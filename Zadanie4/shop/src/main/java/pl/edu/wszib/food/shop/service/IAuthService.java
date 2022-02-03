package pl.edu.wszib.food.shop.service;

import pl.edu.wszib.food.shop.model.Ruser;
import pl.edu.wszib.food.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface IAuthService {
    void login(String login, String password);
    void register(Ruser registerUser);
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
    List<User> getUsers();
    void updateUserName(int id, String name);
}
