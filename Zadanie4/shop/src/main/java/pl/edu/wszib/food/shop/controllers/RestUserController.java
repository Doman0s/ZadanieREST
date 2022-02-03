package pl.edu.wszib.food.shop.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.food.shop.model.User;
import pl.edu.wszib.food.shop.service.IAuthService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class RestUserController {
    @Autowired
    IAuthService authService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.authService.getUsers();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Optional<User> getUserById(@PathVariable int id) {
        return this.authService.getUserById(id);
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public Optional<User> getUserByLogin(@PathVariable String login) {
        return this.authService.getUserByLogin(login);
    }

    @RequestMapping(value = "/update/{id}/{name}", method = RequestMethod.POST)
    public void updateUser(@PathVariable int id,
                           @PathVariable String name) {
        this.authService.updateUserName(id, name);
    }

    @RequestMapping(value = "/add/{name}/{surname}/{login}/{password}", method = RequestMethod.POST)
    public void addUser(@PathVariable String name,
                        @PathVariable String surname,
                        @PathVariable String login,
                        @PathVariable String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(DigestUtils.md5Hex(password));

        this.authService.addUser(user);
    }
}
