package uz.ataboyev.debtbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.ataboyev.debtbook.entity.DebtList;
import uz.ataboyev.debtbook.entity.Debtor;
import uz.ataboyev.debtbook.payload.DebtListReqDto;
import uz.ataboyev.debtbook.payload.DebtListResDto;
import uz.ataboyev.debtbook.payload.DebtorDto;
import uz.ataboyev.debtbook.payload.DebtorResDto;

@Mapper(componentModel = "spring")
public interface DebtListMapper {



//    @Mapping(target = "debtor",ignore = true)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "debtSum",ignore = true)
    DebtList dtoToDebtList(DebtListReqDto debtListReqDto);

    DebtListResDto debtListToResDto(DebtList debtList);
}
