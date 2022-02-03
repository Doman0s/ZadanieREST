package pl.edu.wszib.food.shop.service;

import pl.edu.wszib.food.shop.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> getLoggedUserOrders();
    void confirmOrder();
    List<Order> getOrdersByUserId(int userId);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(int id);
}
