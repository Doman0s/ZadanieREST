package pl.edu.wszib.food.shop.database;

import pl.edu.wszib.food.shop.model.Product;
import pl.edu.wszib.food.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
    List<User> getUsers();
    void updateUserName(int id, String name);
}
