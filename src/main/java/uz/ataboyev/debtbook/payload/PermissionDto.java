package uz.ataboyev.debtbook.payload;

import uz.ataboyev.debtbook.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PermissionDto {

    private String enumName; //proektdagi ishlatiladigan name
    private String name;//frontda ko'rinadigan ism
    private String description;


    public PermissionDto(PermissionEnum permissionEnum) {
        this.enumName = permissionEnum.name();
        this.name = permissionEnum.getName();
        this.description = permissionEnum.getDescription();
    }
}
