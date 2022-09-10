package uz.ataboyev.debtbook.repository;

import uz.ataboyev.debtbook.entity.Permission;
import uz.ataboyev.debtbook.enums.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByNameEnum(PermissionEnum nameEnum);

    @Query(value = "select *from  permission p where p.name_enum in(:permissions)", nativeQuery = true)
    List<Permission> getPermissionList(@Param("permissions") List<PermissionEnum> permissionEnums);

    Set<Permission> findByNameEnumIn(Set<PermissionEnum> nameEnum);

}
