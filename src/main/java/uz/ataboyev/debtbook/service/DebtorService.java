package uz.ataboyev.debtbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.payload.ApiResult;
import uz.ataboyev.debtbook.payload.DebtorDto;
import uz.ataboyev.debtbook.payload.DebtorResDto;

@Service
public interface DebtorService {
    ApiResult<?> add(DebtorDto debtorDto);

    ApiResult<DebtorResDto> getOne(Long id);

    ApiResult<?> getAll();

    ApiResult<?> edit(Long id, DebtorDto debtorDto, User user);

    ApiResult<?> delete(Long id, User user);
}
