package az.edu.turing.msaccount.service;

import az.edu.turing.msaccount.domain.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(Long categoryId);

    Product getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);
}
