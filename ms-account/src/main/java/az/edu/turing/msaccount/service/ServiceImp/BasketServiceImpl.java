package az.edu.turing.msaccount.service.ServiceImp;

import az.edu.turing.msaccount.domain.entity.Basket;
import az.edu.turing.msaccount.domain.entity.Product;
import az.edu.turing.msaccount.domain.repository.BasketRepository;
import az.edu.turing.msaccount.domain.repository.ProductRepository;
import az.edu.turing.msaccount.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Override
    public Basket getBasketByUserId(Long userId) {

        if (basketRepository.findByUserId(userId) != null) {
            return basketRepository.findByUserId(userId);
        } else {
           return createNewBasket(userId);
        }
    }

    @Override
    public Basket addProductToBasket(Long userId, Product product) {
        Basket basket = getBasketByUserId(userId);


        if (basket.getProducts() == null) {
            basket.setProducts(new ArrayList<>());
        }

        basket.getProducts().add(product);
        updateTotalPrice(basket);

        return basketRepository.save(basket);
    }

    @Override
    public void removeProductFromBasket(Long userId, Long productId) {
        Basket basket = getBasketByUserId(userId);


        basket.getProducts().removeIf(p -> p.getId().equals(productId));
        updateTotalPrice(basket);

        basketRepository.save(basket);
    }

    @Override
    public void clearBasket(Long userId) {
        Basket basket = getBasketByUserId(userId);

        basket.getProducts().clear();
        basket.setTotalPrice(BigDecimal.ZERO);

        basketRepository.save(basket);
    }

    private void updateTotalPrice(Basket basket) {
        BigDecimal total = basket.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        basket.setTotalPrice(total);
    }

    private Basket createNewBasket(Long userId) {
        Basket newBasket = new Basket();
        newBasket.setId(userId);
        newBasket.setTotalPrice(BigDecimal.ZERO);
        newBasket.setProducts(new ArrayList<>());
        return basketRepository.save(newBasket);
    }
}
