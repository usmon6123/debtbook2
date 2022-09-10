package uz.ataboyev.debtbook.repository;

import uz.ataboyev.debtbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByRoleId(Long role_id);

    List<User> findAllByRoleId(Long role_id);
}
