package uz.ataboyev.debtbook.service;

import uz.ataboyev.debtbook.entity.Permission;
import uz.ataboyev.debtbook.entity.Role;
import uz.ataboyev.debtbook.entity.RolePermissionFromUser;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.enums.PermissionEnum;
import uz.ataboyev.debtbook.exception.RestException;
import uz.ataboyev.debtbook.payload.*;
import uz.ataboyev.debtbook.repository.RolePermissionFromUserRepository;
import uz.ataboyev.debtbook.repository.RoleRepository;
import uz.ataboyev.debtbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.debtbook.service.base.BaseService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BaseService baseService;
    private final RolePermissionFromUserRepository rolePermissionFromUserRepository;


    @Value("${dataLoaderMode}")
    private String dataLoaderMode;

    public ApiResult<List<UserDtoByRole>> getAllByRole(Long roleId) {

        //TEPADA KELGAN ROLEGA OLGAN USERLAR RO'YHATINI QAYTARADI
        List<User> userList = userRepository.findAllByRoleId(roleId);

        //RO'YHAT BO'SH BO'LSA BO'SH LIST QAYTARAMIZ
        if (userList.isEmpty()) return ApiResult.successResponse(new ArrayList<>());

        //USERLARNI USERNAME VA ID LARINI DTOGA O'RAB LISTGA YIG'IB QAYTARYAPMIZ
        List<UserDtoByRole> collect = userList.stream().map(UserDtoByRole::new).collect(Collectors.toList());
        return ApiResult.successResponse(collect);
    }

    public ApiResult<?> delete(UUID id) {
        if (existsUserOrElseThrow(id))
            userRepository.deleteById(id);

        return ApiResult.successResponse("DELETED_USER");

    }

    @Transactional
    public ApiResult<?> editUserRole(UserRoleDto userRoleDto) {

        //ID ORQALI BAZADAN USER QAYTARADI AKS HOLDA THROW
        User user = getUserOrElseThrow(userRoleDto.getUserId());

        //ID ORQALI ROLENI OLIB KELYAPMIZ AKS HOLDA THROW
        Role role = roleRepository.findById(userRoleDto.getRoleId()).orElseThrow(() -> new RestException("ROLE_NOTE_FOUND", HttpStatus.NOT_FOUND));

        //USERNING ESKI ROLE PERMISSIONLARINI O'CHIRIB TASHLAYAPMIZ
        rolePermissionFromUserRepository.deletedByUserId(userRoleDto.getUserId());

        user.setRole(role);

        userRepository.save(user);

        //ROLE GA SISTEMADA QANDAY ISHLAR QILA OLISHLIGINI SAQLAYAPMIZ
        saveUserRolePermission(userRoleDto);

        return ApiResult.successResponse("SUCCESS_EDITED");
    }

    public ApiResult<?> getOne(UUID id) {

        User user = getUserOrElseThrow(id);
        RoleResDto roleResDto = new RoleResDto(user.getRole());

        Set<PermissionEnum> permissions = baseService.getPermissionsByUserIdAndRoleId(id, user.getRole().getId());

        roleResDto.setPermissions(permissions);
        UserDto userDto = new UserDto(user.getUsername(),roleResDto);

        return ApiResult.successResponse(userDto);
    }

//    ---------------------------helper method----------------------------
    //BU METHOD KIRIB KELGAN IDLI USER BAZADA BO'LSA TRUE AKS HOLDA THROWGA OTADI

    public boolean existsUserOrElseThrow(UUID userId) {

        //AGAR O'CHIRMOQCHI BO'LGAN USERIMIZ TOPILMASA THROW
        if (!userRepository.existsById(userId)) throw RestException.restThrow("USER_NOT_FOUND", HttpStatus.NOT_FOUND);
        return true;
    }
    //ID ORQALI BAZADAN USER QAYTARADI AKS HOLDA THROW

    public User getUserOrElseThrow(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RestException("USER_NOT_FOUND", HttpStatus.NOT_FOUND));
        return user;
    }

    public void saveUserRolePermission(UserRoleDto userRoleDto){

        Set<Permission> permissions = baseService.getPermissions(userRoleDto.getPermissionEnumList());

        rolePermissionFromUserRepository.save(
                new RolePermissionFromUser(
                        userRoleDto.getUserId(),
                        userRoleDto.getRoleId(),
                        permissions
                       ));
    }

//    public ApiResult<?> editUser(UUID id, UserDto userDto) {
//        System.out.println(id);
//        System.out.println(userDto.getUsername());
//        //todo edit user vaziyatga qarab yozildai
//        return null;
//    }
}
