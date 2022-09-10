package uz.ataboyev.debtbook.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.ataboyev.debtbook.entity.template.AbsLongEntity;
import uz.ataboyev.debtbook.enums.DebtorStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DebtList extends AbsLongEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Debtor debtor;

    private BigDecimal debtSum;

    @Column(columnDefinition = "text")
    private String description;

    //TRUE -> QARZDOR QARZ OLSA, FALSE -> QARZDOR QARZINI TO'LAMOQCHI BO'LSA
    private boolean inOrOut = false;

    //QARZNI QAYSI SOTUVCHI BERGANINI BILISH UCHUN
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
