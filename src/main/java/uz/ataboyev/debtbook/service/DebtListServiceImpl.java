package uz.ataboyev.debtbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ataboyev.debtbook.entity.DebtList;
import uz.ataboyev.debtbook.entity.Debtor;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.exception.RestException;
import uz.ataboyev.debtbook.mapper.DebtListMapper;
import uz.ataboyev.debtbook.payload.ApiResult;
import uz.ataboyev.debtbook.payload.DebtListReqDto;
import uz.ataboyev.debtbook.payload.DebtListResDto;
import uz.ataboyev.debtbook.repository.DebtListRepository;
import uz.ataboyev.debtbook.repository.DebtorRepository;
import uz.ataboyev.debtbook.service.base.BaseService;

import java.math.BigDecimal;
import java.math.MathContext;

@Service
@RequiredArgsConstructor
public class DebtListServiceImpl implements DebtListService {

    private final BaseService baseService;
    private final DebtListMapper debtListMapper;
    private final DebtListRepository debtListRepository;
    private final DebtorRepository debtorRepository;

    @Override
    public ApiResult<DebtListResDto> add(DebtListReqDto debtListReqDto, User user) {

        //QARZDORNI OLIB KELYABDI BAZADAN
        Debtor currentDebtor = baseService.getDebtorByIdElseThrow(debtListReqDto.getDebtorId());

        //QARZ OLDI BERDISINI BAZASGA SAQLASH
        DebtList debtList = debtListMapper.dtoToDebtList(debtListReqDto);

        //bazaga qarz olinayotgan bo'lsa -(minus)dan saqlanadi qiymat
        if (debtListReqDto.isInOrOut() == false)
            debtList.setDebtSum(new BigDecimal(-1*debtListReqDto.getDebtSum(),MathContext.DECIMAL64));
        else debtList.setDebtSum(new BigDecimal(debtListReqDto.getDebtSum(),MathContext.DECIMAL64));

        //BU YERDA QARZDORNING ACCAUNTIGA UMUMIYQARZINI YOZIB QO'YYAPMIZ
        BigDecimal debtSums = baseService.getDebtsForCurrentDebtor(currentDebtor.getId());
        currentDebtor.setDebtSums(debtSums);
        debtorRepository.save(currentDebtor);

        //bazaga qarz saqlanyapdi
        debtList.setDebtor(currentDebtor);
        debtList.setUser(user);
        debtListRepository.save(debtList);


        DebtListResDto debtListResDto = debtListMapper.debtListToResDto(debtList);
        debtListResDto.setAllDebt(debtSums);
        debtListResDto.setDebtorId(debtListReqDto.getDebtorId());
        debtListResDto.setUserId(user.getId());
        debtListResDto.setUserName(user.getUsername());
        debtListResDto.setCreatedAt(debtList.getCreatedAt());
        return ApiResult.successResponse(debtListResDto,"success added");
    }

    @Override
    public ApiResult<?> edit(Long id, DebtListReqDto debtListReqDto, User user) {
        DebtList debtList = debtListRepository.findById(id).orElseThrow(() -> new RestException("qarz topilmadi", HttpStatus.NOT_FOUND));
        Debtor debtor = baseService.getDebtorByIdElseThrow(debtListReqDto.getDebtorId());

        //BU YERDA QARZDORNING ACCAUNTIGA UMUMIYQARZINI YOZIB QO'YYAPMIZ
        BigDecimal debtSums = baseService.getDebtsForCurrentDebtor(debtor.getId());
        debtor.setDebtSums(debtSums);
        debtorRepository.save(debtor);


        debtList.setDebtStatus(debtListReqDto.getDebtStatus());
        debtList.setDebtSum(BigDecimal.valueOf(debtListReqDto.getDebtSum()));
        debtList.setDebtor(debtor);
        debtList.setInOrOut(debtList.isInOrOut());
        debtList.setDebtStatus(debtListReqDto.getDebtStatus());
        debtList.setDescription(debtListReqDto.getDescription());
        debtList.setUser(user);
        debtListRepository.save(debtList);
        return ApiResult.successResponse("SUCCESS EDITED");
    }

    @Override
    public ApiResult<?> getOne(Long id) {
        DebtList debtList = debtListRepository.findById(id).orElseThrow(() -> new RestException("Not found DebtList", HttpStatus.NOT_FOUND));
        DebtListResDto debtListResDto = debtListMapper.debtListToResDto(debtList);
        return ApiResult.successResponse(debtListResDto);
    }
}
