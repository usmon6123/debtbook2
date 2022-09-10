package uz.ataboyev.debtbook.entity;

import lombok.*;
import uz.ataboyev.debtbook.entity.template.AbsLongEntity;
import uz.ataboyev.debtbook.enums.DebtorStatus;

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
public class Debtor extends AbsLongEntity {


    private String name;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private DebtorStatus debtorStatus;

    @Column(columnDefinition = "text")
    private String description;
}
