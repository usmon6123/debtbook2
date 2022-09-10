package uz.ataboyev.debtbook.controller;

import uz.ataboyev.debtbook.aop.annotation.CheckPermission;
import uz.ataboyev.debtbook.payload.ApiResult;
import uz.ataboyev.debtbook.service.PermissionService;
import uz.ataboyev.debtbook.utils.RestConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstant.PERMISSION_CONTROLLER)
public class PermissionController {
    private final PermissionService permissionService;

    @CheckPermission(values = {"GET_ROLES"})
    @GetMapping("/get-all")
    ApiResult<?>getAll(){
       return permissionService.getAll();
    }
}
