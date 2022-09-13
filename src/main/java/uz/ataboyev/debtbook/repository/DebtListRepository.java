package uz.ataboyev.debtbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.debtbook.entity.DebtList;
import uz.ataboyev.debtbook.entity.Debtor;

import java.math.BigDecimal;
import java.util.List;

public interface DebtListRepository extends JpaRepository<DebtList, Long> {

    @Query(value = "select sum(debt_sum) from debt_list where debtor_id = :id",nativeQuery = true)
    BigDecimal getSumDebtsForDebtor(@Param("id")Long debtorId);

    @Query(value = "select * from debt_list where :myQuery",nativeQuery = true)
    List<DebtList>getFilterSearchSortDebtList(@Param("myQuery")String myQuery);


}
