package pl.edu.wszib.food.shop.service.implementation;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wszib.food.shop.database.IUserDAO;
import pl.edu.wszib.food.shop.exceptions.LoginUsedException;
import pl.edu.wszib.food.shop.model.Ruser;
import pl.edu.wszib.food.shop.model.User;
import pl.edu.wszib.food.shop.service.IAuthService;
import pl.edu.wszib.food.shop.session.Session;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    @Autowired
    IUserDAO userDAO;

    @Resource
    Session session;

    @Override
    public void login(String login, String password) {
        Optional<User> user = this.userDAO.getUserByLogin(login);

        if(user.isEmpty() || !user.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            return;
        }
        this.session.setUser(user.get());
    }

    @Override
    public void register(Ruser ruser) {
        Optional<User> user = this.userDAO.getUserByLogin(ruser.getLogin());

        if(user.isPresent()) {
            throw new LoginUsedException();
        }

        ruser.setPassword(DigestUtils.md5Hex(ruser.getPassword()));

        User userNew = new User();
        userNew.setName(ruser.getName());
        userNew.setSurname(ruser.getSurname());
        userNew.setLogin(ruser.getLogin());
        userNew.setPassword(ruser.getPassword());

        this.userDAO.addUser(userNew);
    }

    @Override
    public List<User> getUsers() {
        return this.userDAO.getUsers();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return this.userDAO.getUserByLogin(login);
    }

    @Override
    public void updateUserName(int id, String name) {
        this.userDAO.updateUserName(id, name);
    }

    @Override
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }
}
