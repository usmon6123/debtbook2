package uz.ataboyev.debtbook.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data@AllArgsConstructor@NoArgsConstructor
public class DebtorHistoryResDto {


    //TRUE -> QARZDOR QARZ OLSA, FALSE -> QARZDOR QARZINI TO'LAMOQCHI BO'LSA
    private Long id;
    private boolean inOrOut = false;
    private BigDecimal sum;
    private Timestamp createdAt;
    private String description;
}
