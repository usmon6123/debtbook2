package uz.ataboyev.debtbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uz.ataboyev.debtbook.entity.Debtor;
import uz.ataboyev.debtbook.entity.Role;
import uz.ataboyev.debtbook.payload.DebtorDto;
import uz.ataboyev.debtbook.payload.RoleDto;
import uz.ataboyev.debtbook.payload.RoleResDto;

@Mapper(componentModel = "spring")
public interface DebtorMapper {

    @Mapping(target = "id",ignore = true)
    Debtor dtoToDebtor(DebtorDto debtorDto);


}
