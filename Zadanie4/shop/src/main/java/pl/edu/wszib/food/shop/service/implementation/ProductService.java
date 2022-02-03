package pl.edu.wszib.food.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.food.shop.database.IProductDAO;
import pl.edu.wszib.food.shop.model.Product;
import pl.edu.wszib.food.shop.service.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductDAO productDAO;

    public List<Product> getAllProducts() {
        return this.productDAO.getProducts();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return this.productDAO.getProductById(id);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return this.productDAO.getProductByName(name);
    }

    public void updateProductName(int id, String name) {
        this.productDAO.updateProductName(id, name);
    }

    public void addProduct(Product product) {
        this.productDAO.addProduct(product);
    }
}
