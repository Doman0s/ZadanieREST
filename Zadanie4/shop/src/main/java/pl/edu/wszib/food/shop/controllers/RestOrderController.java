package pl.edu.wszib.food.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.food.shop.model.Order;
import pl.edu.wszib.food.shop.service.IOrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class RestOrderController {
    @Autowired
    IOrderService orderService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Optional<Order> getOrderById(@PathVariable int id) {
        return this.orderService.getOrderById(id);
    }

    @RequestMapping(value = "/user_id/{user_id}", method = RequestMethod.GET)
    public List<Order> getOrdersByUserId(@PathVariable int user_id) {
        return this.orderService.getOrdersByUserId(user_id);
    }
}
