package uz.ataboyev.debtbook.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import uz.ataboyev.debtbook.entity.Role;
import uz.ataboyev.debtbook.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor@NoArgsConstructor@Data@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResDto {

    private Long id;

    private String name;

    private String description;

    private Set<PermissionEnum> permissions;

//todo role per
    public RoleResDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
    }
}
