package uz.ataboyev.debtbook.service.base;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.debtbook.entity.Permission;
import uz.ataboyev.debtbook.enums.PermissionEnum;
import uz.ataboyev.debtbook.exception.RestException;
import uz.ataboyev.debtbook.repository.PermissionRepository;
import uz.ataboyev.debtbook.repository.RolePermissionFromUserRepository;
import uz.ataboyev.debtbook.repository.RoleRepository;
import uz.ataboyev.debtbook.repository.UserRepository;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class BaseService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RolePermissionFromUserRepository rolePermissionFromUserRepository;
    private final PermissionRepository permissionRepository;

    //USERNING BARCHA PERMISSIONLARINI QAYTARADIGAN YO'L
    public Set<PermissionEnum> getPermissionsByUserIdAndRoleId(UUID userId, Long roleId) {

        //BAZADA ENUMLARNING NAMEI SAQLANADI, USERNING PERMISSIONLARINI OLIB KELADI
        Set<String> permissionName = rolePermissionFromUserRepository.allPermissionByUserIdAndRoleId(userId, roleId);

        //BAZADAN KELGAN PERMISSIONLARNI ENUMGA O'GIRYAPDI
        Set<PermissionEnum> enumSet = Arrays.stream(PermissionEnum.values()).filter(permissionEnum -> permissionName.stream().anyMatch(s -> s.equals(permissionEnum.name()))).collect(Collectors.toSet());

        return enumSet;
    }



    //BAZADAN ENUM LIST BO'YICHA PERMISSIONLARNI OLIB KELIBERDI
    public Set<Permission> getPermissions(Set<PermissionEnum> permissionSet) {
        return permissionRepository.findByNameEnumIn(permissionSet);
    }

    //BAZADAN ENUM LIST BO'YICHA PERMISSIONNI OLIB KELADI AKS HOLDA THROW
    public Permission getPermission(PermissionEnum permission) {
        return permissionRepository.findByNameEnum(permission).orElseThrow(() -> new RestException("PERMISSION NOT FOUND", HttpStatus.NOT_FOUND));
    }
}
