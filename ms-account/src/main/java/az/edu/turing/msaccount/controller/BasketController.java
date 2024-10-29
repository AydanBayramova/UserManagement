package az.edu.turing.msaccount.controller;

import az.edu.turing.msaccount.domain.entity.Basket;
import az.edu.turing.msaccount.domain.entity.Product;
import az.edu.turing.msaccount.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/baskets")
@RequiredArgsConstructor
@Tag(name = "Basket Management", description = "Operations related to basket management")
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/{userId}")
    @Operation(summary = "Get basket by user ID", description = "Retrieve the basket for a given user ID")
    public ResponseEntity<Basket> getBasketByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(basketService.getBasketByUserId(userId));
    }

    @PostMapping("/{userId}/add")
    @Operation(summary = "Add product to basket", description = "Add a product to the user's basket")
    public ResponseEntity<Basket> addProductToBasket(@PathVariable Long userId, @RequestBody Product product) {
        return ResponseEntity.ok(basketService.addProductToBasket(userId, product));
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    @Operation(summary = "Remove product from basket", description = "Remove a product from the user's basket by product ID")
    public ResponseEntity<Void> removeProductFromBasket(@PathVariable Long userId, @PathVariable Long productId) {
        basketService.removeProductFromBasket(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/clear")
    @Operation(summary = "Clear basket", description = "Remove all products from the user's basket")
    public ResponseEntity<Void> clearBasket(@PathVariable Long userId) {
        basketService.clearBasket(userId);
        return ResponseEntity.noContent().build();
    }
}
