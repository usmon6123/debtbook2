package uz.ataboyev.debtbook.payload;

import uz.ataboyev.debtbook.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor@NoArgsConstructor@Data
public class RoleDto {


    private String name;

    private String description;

    private Set<PermissionEnum> permissions;


    public RoleDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
