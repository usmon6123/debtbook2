package uz.ataboyev.debtbook.entity;

import uz.ataboyev.debtbook.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role extends AbsLongEntity {

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

//    @Enumerated(EnumType.STRING)
//    @ElementCollection
//    private Set<PermissionEnum> permissions;
}
