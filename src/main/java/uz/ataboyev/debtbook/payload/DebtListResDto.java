package uz.ataboyev.debtbook.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ataboyev.debtbook.enums.PayTypeEnum;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor@NoArgsConstructor@Data
public class DebtListResDto {

    private long id;

    private long debtorId;

    private Double debtSum;

    //FALSE -> QARZDOR QARZ OLSA, TRUE -> QARZDOR QARZINI TO'LAMOQCHI BO'LSA
    private boolean inOrOut = false;

    private PayTypeEnum debtStatus;

    @Column(columnDefinition = "text")
    private String description;

    //QARZDORGA HIZMAT KORSATGAN ISHCHI MA'LUMOTLARI
    private UUID userId;
    private String userName;

    private BigDecimal allDebt;

    private Timestamp createdAt;//OBJECT YANGI OCHILGANDA ISHLAYDI
}
