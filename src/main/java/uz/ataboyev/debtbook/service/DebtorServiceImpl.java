package uz.ataboyev.debtbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.debtbook.entity.*;
import uz.ataboyev.debtbook.enums.PermissionEnum;
import uz.ataboyev.debtbook.exception.RestException;
import uz.ataboyev.debtbook.mapper.DebtListMapper;
import uz.ataboyev.debtbook.mapper.DebtorMapper;
import uz.ataboyev.debtbook.payload.*;
import uz.ataboyev.debtbook.repository.DebtorRepository;
import uz.ataboyev.debtbook.repository.RolePermissionFromUserRepository;
import uz.ataboyev.debtbook.repository.RoleRepository;
import uz.ataboyev.debtbook.repository.UserRepository;
import uz.ataboyev.debtbook.service.base.BaseService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DebtorServiceImpl implements DebtorService{

    private final DebtorRepository debtorRepository;
    private final DebtorMapper debtorMapper;
    private final BaseService baseService;
    private final DebtListMapper debtListMapper;

    @Override
    public ApiResult<?> add(DebtorDto debtorDto) {

        //BAZADA BOR YO'QLIGINI TEKSHIRISH QARZDORNING, BOR BO'LSA THROW
        boolean existsByPhoneNumberOrName = debtorRepository.existsByPhoneNumberOrName(debtorDto.getPhoneNumber(), debtorDto.getName());
        if (existsByPhoneNumberOrName)throw new RestException("Ismi yoki Telefon raqam bazada mavjud",HttpStatus.CONFLICT);

        Debtor debtor = debtorMapper.dtoToDebtor(debtorDto);
        debtorRepository.save(debtor);

        BigDecimal debts = baseService.getDebtsForCurrentDebtor(debtor.getId());
        DebtorResDto debtorResDto = debtorMapper.debtorToResDto(debtor);
        debtorResDto.setDebtSums(debts);


        return ApiResult.successResponse(debtorResDto,"SAVED DEBTOR");
    }

    @Override
    public ApiResult<?> edit(Long id, DebtorDto debtorDto) {

        //ID BO'YICHA QARZDOR BAZADAN OLIB KELINADI
        Debtor debtor = baseService.getDebtorByIdElseThrow(id);

        //QARZDOR PARAMETRLARI DTODAGI MA'LUMOTLAR ORQALI O'ZGARTIRILADI
        debtorMapper.updateDebtor(debtor,debtorDto);

        return ApiResult.successResponse("SUCCESS EDITED DEBTOR");
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return null;
    }

    @Override
    public ApiResult<List<DebtorHistoryResDto>> getOneHistory(Long id) {

        //QARZDORNING IDSI ORQALI UMUMIY OLDI BERDILAR ROYHATINI OLIB KELADI
        List<DebtList> listDebtsForDebtorById = baseService.getListDebtsForDebtorById(id);
        List<DebtorHistoryResDto> debtorHistoryResDtos = debtListMapper.debtListsToHistoryResDto(listDebtsForDebtorById);
        return ApiResult.successResponse(debtorHistoryResDtos);
    }

    @Override
    public ApiResult<DebtorResDto> getOne(Long id) {
        Debtor debtor = debtorRepository.findById(id).orElseThrow(() -> new RestException("Bu qarzdor topilmadi", HttpStatus.NOT_FOUND));
        //debtorni umumiy qarzini olib kelish kerak

        DebtorResDto debtorResDto = debtorMapper.debtorToResDto(debtor);

        return ApiResult.successResponse(debtorResDto);
    }

    @Override
    public ApiResult<?> getAll() {
        return null;
    }



}
