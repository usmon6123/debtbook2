package uz.ataboyev.debtbook.service;

import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.payload.ApiResult;
import uz.ataboyev.debtbook.payload.DebtListReqDto;
import uz.ataboyev.debtbook.payload.DebtListResDto;

public interface DebtListService {
    ApiResult<DebtListResDto> add(DebtListReqDto debtListReqDto, User user);

    ApiResult<?> edit(Long id, DebtListReqDto debtListReqDto, User user);

    ApiResult<?> getOne(Long id);
}
