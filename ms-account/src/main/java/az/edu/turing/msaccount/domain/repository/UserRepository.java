package az.edu.turing.msaccount.domain.repository;

import az.edu.turing.msaccount.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
