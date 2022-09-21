package uz.ataboyev.debtbook.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.ataboyev.debtbook.entity.Debtor;
import uz.ataboyev.debtbook.payload.DebtorResDto;

@Component
@RequiredArgsConstructor
public class CustomMapper {
    private final DebtorMapper debtorMapper;

public static DebtorResDto debtorToResDto(Debtor debtor){

    return new DebtorResDto(
            debtor.getId(),
            debtor.getName(),
            debtor.getPhoneNumber(),
            debtor.getDebtorStatus(),
            debtor.getDescription(),
            debtor.getDebtSums()

    );
}


}
