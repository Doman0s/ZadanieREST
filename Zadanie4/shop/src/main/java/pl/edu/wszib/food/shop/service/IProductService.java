package pl.edu.wszib.food.shop.service;

import pl.edu.wszib.food.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(int productId);
    Optional<Product> getProductByName(String name);
    void updateProductName(int id, String name);
    void addProduct(Product product);
}
