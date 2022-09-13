package uz.ataboyev.debtbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.debtbook.aop.annotation.CheckPermission;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.payload.ApiResult;
import uz.ataboyev.debtbook.payload.DebtorDto;
import uz.ataboyev.debtbook.payload.DebtorResDto;
import uz.ataboyev.debtbook.security.CurrentUser;
import uz.ataboyev.debtbook.service.DebtorService;
import uz.ataboyev.debtbook.utils.RestConstant;

@RestController
@RequestMapping(RestConstant.DEBTOR_CONTROLLER)
@RequiredArgsConstructor
public class DebtorController {

    private final DebtorService debtorService;

    @CheckPermission(values = "ADD_DEBTOR")
    @PostMapping("/add")
    ApiResult<?> add(@RequestBody DebtorDto debtorDto) {

        return debtorService.add(debtorDto);
    }

    @CheckPermission(values = {"GET"})
    @GetMapping("/get-one/{id}")
    ApiResult<DebtorResDto> getOne(@PathVariable Long id) {

        return debtorService.getOne(id);
    }

    @CheckPermission(values = "GET")
    @GetMapping("/get-all")
    ApiResult<?> getAll() {

        return debtorService.getAll();
    }

    //BU YO'LGA ASOSAN ADMIN KIRIB QO'L OSTIDAGILARNING RO'LE VA PERMISSIONLARINI O'ZGARTIRISHI MUMKIN
    @CheckPermission(values = {"EDIT"})
    @PutMapping("/edit/{id}")
    ApiResult<?> edit(@PathVariable Long id, @RequestBody DebtorDto debtorDto) {
        return debtorService.edit(id, debtorDto);
    }

    @CheckPermission(values = "DELETE")
    @DeleteMapping("/delete/{id}")
    ApiResult<?> delete(@PathVariable Long id) {
        return debtorService.delete(id);
    }



}
