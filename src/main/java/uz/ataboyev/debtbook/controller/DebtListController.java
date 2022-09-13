package uz.ataboyev.debtbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.ataboyev.debtbook.aop.annotation.CheckPermission;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.payload.*;
import uz.ataboyev.debtbook.security.CurrentUser;
import uz.ataboyev.debtbook.service.DebtListService;
import uz.ataboyev.debtbook.service.DebtorService;
import uz.ataboyev.debtbook.utils.RestConstant;

@RestController
@RequestMapping(RestConstant.DEBT_LIST_CONTROLLER)
@RequiredArgsConstructor
public class DebtListController {

    private final DebtListService debtListService;

    @CheckPermission(values = "ADD_DEBT_LIST")
    @PostMapping("/add")
    ApiResult<DebtListResDto> add(@RequestBody DebtListReqDto debtListReqDto, @CurrentUser User user) {
        return debtListService.add(debtListReqDto,user);
    }






    @CheckPermission(values = {"GET"})
    @GetMapping("/get-one")
    ApiResult<?> getOne(@PathVariable Long id) {

        return null;
    }

}
