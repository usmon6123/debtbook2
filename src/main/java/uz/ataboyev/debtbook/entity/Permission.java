package uz.ataboyev.debtbook.entity;

import uz.ataboyev.debtbook.entity.template.AbsLongEntity;
import uz.ataboyev.debtbook.enums.PermissionEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Permission extends AbsLongEntity {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private PermissionEnum nameEnum;

//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @ManyToMany(mappedBy = "permissions")
//    private List<RolePermissionFromUser> rolePermissionFromUser;
//
//    public Permission(PermissionEnum nameEnum) {
//        this.nameEnum = nameEnum;
//    }
}
