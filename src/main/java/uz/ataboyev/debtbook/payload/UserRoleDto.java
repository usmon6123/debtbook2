package uz.ataboyev.debtbook.payload;

import uz.ataboyev.debtbook.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleDto {

    private UUID userId;

    private Long roleId;

    private Set<PermissionEnum> permissionEnumList;
}
