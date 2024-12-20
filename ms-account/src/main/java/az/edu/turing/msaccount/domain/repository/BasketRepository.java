package az.edu.turing.msaccount.domain.repository;

import az.edu.turing.msaccount.domain.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByUserId(Long userId);
}
