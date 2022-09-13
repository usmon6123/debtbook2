package uz.ataboyev.debtbook.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.ataboyev.debtbook.entity.Debtor;
import uz.ataboyev.debtbook.entity.User;
import uz.ataboyev.debtbook.enums.DebtorStatus;
import uz.ataboyev.debtbook.enums.PayTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DebtListReqDto {

    private long debtorId;

    private Double debtSum;

    //FALSE -> QARZDOR QARZ OLSA, TRUE -> QARZDOR QARZINI TO'LAMOQCHI BO'LSA
    private boolean inOrOut = false;

    private PayTypeEnum debtStatus;

    @Column(columnDefinition = "text")
    private String description;
}
