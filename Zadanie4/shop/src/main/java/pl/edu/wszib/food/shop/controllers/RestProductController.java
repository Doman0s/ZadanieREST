package pl.edu.wszib.food.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.food.shop.model.Product;
import pl.edu.wszib.food.shop.service.IProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/product")
public class RestProductController {
    @Autowired
    IProductService productService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Optional<Product> getProductById(@PathVariable int id) {
        return this.productService.getProductById(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Optional<Product> getProductByName(@PathVariable String name) {
        return this.productService.getProductByName(name);
    }

    @RequestMapping(value = "/update/{id}/{name}", method = RequestMethod.POST)
    public void updateProductName(@PathVariable int id,
                           @PathVariable String name) {
        this.productService.updateProductName(id, name);
    }

    @RequestMapping(value = "/add/{name}/{description}/{price}/{quantity}", method = RequestMethod.POST)
    public void addProduct(@PathVariable String name,
                        @PathVariable String description,
                        @PathVariable double price,
                        @PathVariable int quantity) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        this.productService.addProduct(product);
    }
}
