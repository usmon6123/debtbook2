package uz.ataboyev.debtbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.debtbook.aop.annotation.CheckPermission;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.payload.*;
import uz.ataboyev.debtbook.security.CurrentUser;
import uz.ataboyev.debtbook.service.DebtorService;
import uz.ataboyev.debtbook.utils.RestConstant;

import java.util.List;

import static uz.ataboyev.debtbook.utils.AppConstant.DEFAULT_PAGE_NUMBER;
import static uz.ataboyev.debtbook.utils.AppConstant.DEFAULT_PAGE_SIZE;

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

    @CheckPermission(values = {"GET"})
    @GetMapping("/get-one-history/{id}")
    ApiResult<List<DebtorHistoryResDto>> getOneHistory(@PathVariable Long id) {
        return debtorService.getOneHistory(id);
    }

    @CheckPermission(values = "GET")
    @GetMapping("/get-all")
    ApiResult<CustomPage<DebtorResDto>> getAll(@RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
                                               @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size) {

        return debtorService.getAll(page,size);
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
