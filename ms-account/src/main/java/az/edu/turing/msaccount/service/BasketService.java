package az.edu.turing.msaccount.service;

import az.edu.turing.msaccount.domain.entity.Basket;
import az.edu.turing.msaccount.domain.entity.Product;

public interface BasketService {
    Basket getBasketByUserId(Long userId);
    Basket addProductToBasket(Long userId, Product product);
    void removeProductFromBasket(Long userId, Long productId);
    void clearBasket(Long userId);
}
