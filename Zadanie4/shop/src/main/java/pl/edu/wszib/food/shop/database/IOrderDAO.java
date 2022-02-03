package pl.edu.wszib.food.shop.database;

import pl.edu.wszib.food.shop.model.Order;
import pl.edu.wszib.food.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
    List<Order> getOrders();
    Optional<Order> getOrderById(int id);
}
