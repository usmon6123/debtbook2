package uz.ataboyev.debtbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.ataboyev.debtbook.entity.Debtor;
import uz.ataboyev.debtbook.entity.Permission;
import uz.ataboyev.debtbook.enums.PermissionEnum;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DebtorRepository extends JpaRepository<Debtor, Long> {


   boolean existsByName(String name);
   boolean existsByPhoneNumberOrName(String phoneNumber, String name);
}
