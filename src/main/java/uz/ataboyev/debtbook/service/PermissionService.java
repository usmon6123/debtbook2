package uz.ataboyev.debtbook.service;

import uz.ataboyev.debtbook.enums.PermissionEnum;
import uz.ataboyev.debtbook.payload.*;
import uz.ataboyev.debtbook.repository.RolePermissionFromUserRepository;
import uz.ataboyev.debtbook.repository.RoleRepository;
import uz.ataboyev.debtbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ataboyev.debtbook.service.base.BaseService;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BaseService baseService;
    private final RolePermissionFromUserRepository rolePermissionFromUserRepository;


    public ApiResult<?> getAll() {

        PermissionEnum[] permissionEnums = PermissionEnum.values();

        Set<PermissionDto> permissionDtos = Arrays.stream(permissionEnums).map(PermissionDto::new).collect(Collectors.toSet());

        return ApiResult.successResponse(permissionDtos);

    }


}
