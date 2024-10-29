package az.edu.turing.msaccount.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
