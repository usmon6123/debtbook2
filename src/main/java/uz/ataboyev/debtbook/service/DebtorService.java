package uz.ataboyev.debtbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.payload.ApiResult;
import uz.ataboyev.debtbook.payload.DebtorDto;
import uz.ataboyev.debtbook.payload.DebtorHistoryResDto;
import uz.ataboyev.debtbook.payload.DebtorResDto;

import java.util.List;

@Service
public interface DebtorService {
    ApiResult<?> add(DebtorDto debtorDto);

    ApiResult<DebtorResDto> getOne(Long id);

    ApiResult<?> getAll();

    ApiResult<?> edit(Long id, DebtorDto debtorDto);

    ApiResult<?> delete(Long id);

    ApiResult<List<DebtorHistoryResDto>> getOneHistory(Long id);

}
