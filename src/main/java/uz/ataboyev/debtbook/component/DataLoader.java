package uz.ataboyev.debtbook.component;


import uz.ataboyev.debtbook.entity.Permission;
import uz.ataboyev.debtbook.entity.Role;
import uz.ataboyev.debtbook.entity.RolePermissionFromUser;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.enums.PermissionEnum;
import uz.ataboyev.debtbook.repository.PermissionRepository;
import uz.ataboyev.debtbook.repository.RolePermissionFromUserRepository;
import uz.ataboyev.debtbook.repository.RoleRepository;
import uz.ataboyev.debtbook.repository.UserRepository;
import uz.ataboyev.debtbook.service.base.BaseService;
import uz.ataboyev.debtbook.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static uz.ataboyev.debtbook.enums.PermissionEnum.*;

;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final RolePermissionFromUserRepository rolePermissionFromUserRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;
    private final BaseService baseService;


    public static String ROLE_ADMIN = AppConstant.ADMIN;
    public static String ROLE_USER = AppConstant.USER;

    private final static String userAdmin = "admin";

    @Value("${dataLoaderMode}")
    private String dataLoaderMode;



    @Override
    public void run(String... args) throws Exception {

        if (dataLoaderMode.equals("always")) {

            //BAZAGA BARCHA ENUMLARNI SAQLAYAPMIZ
            Set<Permission> permissions = Arrays.stream(PermissionEnum.values()).sequential().map(Permission::new).collect(Collectors.toSet());
            permissionRepository.saveAll(permissions);

            //ROLLAR YASAB OLIB SAQLAYABMIZ BAZAGA
            Role admin = new Role(AppConstant.ADMIN, "adminchamiz");
            Role user = new Role(AppConstant.USER, "userchiklar");
            roleRepository.save(admin);
            roleRepository.save(user);

            //IKKITA DEFAULT USER YASAB OLIB SAQLAYAPMIZ BAZAGA
            User adminUser = userRepository.save(new User(
                    userAdmin,
                    passwordEncoder.encode("admin123"),
                    admin,
                    true));

            User userDefault = userRepository.save(new User(
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true));


            //SAQLANGAN USERLARGA DEFAULT PERMISSIONLARNI BIRIKTIRIB BAZAGA SAQLAYAPMIZ
            rolePermissionFromUserRepository.save(
                    new RolePermissionFromUser(
                            adminUser.getId(),
                            admin.getId(),
                            permissions));

            Set<PermissionEnum> defaultPermissions = new HashSet<>(Arrays.asList(VIEW,
                    GET,
                    GET_ROLE,
                    GET_USER,
                    DELETE_USER));
            //istalgan kirgan Userga beriladigan Huquqlar
            Set<Permission> permissionSet = baseService.getPermissions(defaultPermissions);

            rolePermissionFromUserRepository.save(
                    new RolePermissionFromUser(
                            userDefault.getId(),
                            user.getId(),
                            permissionSet));

        }
        else {
            //ELSE ISHLAMAYDI!!!
            //QACHONKI PROEKT ISHLAYOTGAN PAYTDA YANGI YO'LLAR QO'SHILSA VA U YO'LLARGA YANGI PERMISSSIONLAR BILAN KIRILADIGAN BO'LSA ELSE ADMINGA BU PERMISSIONLARNI AVTAMAT QO'SHISHI KERAK EDI AMMO OPTIMALLASHTIRILMAY QOLDI
            //YANGI PERMISSIONLAR QO'SHILSA BAZAGA KIRIB TABLGA(ROLE_PERMISSION_FROM_USER_PERMISSIONS) QO'LDA ADMINGA BIRIKTIRIB QO'YING
//
//            User user = userRepository.findByUsername(userAdmin).orElseThrow(() -> new RestException("Sizda asosoiy Admin kiritilmagan", HttpStatus.NOT_FOUND));
//            Set<Permission> permissions = user.getRolePermissionFromUser().getPermissions();
//
//            //BAZADAGI ADMINNING PERMISSIONLARINI ICHIDA BO'LMAGAN PERMISSIONLAR
//            Set<Permission> newPermissions = Arrays.stream(values()).filter(
//                    permissionEnum -> permissions.stream().anyMatch(
//                            permission -> !permission.getNameEnum().equals(permissionEnum))).
//                    map(Permission::new).collect(Collectors.toSet());
//
//
////            List<Permission> savePermissions = permissionRepository.saveAll(newPermissions);
//            RolePermissionFromUser adminPermissionsFromUser = rolePermissionFromUserRepository.findByUserId(user.getId()).orElseThrow(() -> new RestException("Admin Permissions not found", HttpStatus.NOT_FOUND));
        }
    }
}
