package uz.ataboyev.debtbook.mapper;

import uz.ataboyev.debtbook.entity.Role;
import uz.ataboyev.debtbook.payload.RoleDto;
import uz.ataboyev.debtbook.payload.RoleResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id",ignore = true)
    Role dtoToRole(RoleDto roleDto);

    @Mapping(target = "id",ignore = true)
    void updateRoleOutThePermissions(@MappingTarget Role role, RoleDto roleDto);

    RoleResDto roleToRseDto(Role role);

}
