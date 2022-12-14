package uz.ataboyev.debtbook.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.debtbook.enums.DebtorStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor@NoArgsConstructor@Data
public class DebtorResDto {
    private Long id;
    private String name;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private DebtorStatus debtorStatus;

    @Column(columnDefinition = "text")
    private String description;

    private BigDecimal debtSums;

//    private Timestamp date;//qarz olgan yoki bergan vaqti

}
