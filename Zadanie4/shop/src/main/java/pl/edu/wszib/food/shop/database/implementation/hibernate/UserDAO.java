package pl.edu.wszib.food.shop.database.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.food.shop.database.IUserDAO;
import pl.edu.wszib.food.shop.model.Product;
import pl.edu.wszib.food.shop.model.User;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO implements IUserDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.User WHERE login = :login");
        query.setParameter("login", login);
        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(user);
            tr.commit();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> getUserById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.User WHERE id = :id");
        query.setParameter("id", id);
        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public List<User> getUsers() {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.User");
        List<User> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void updateUserName(int id, String name) {
        Session session = this.sessionFactory.openSession();

        Query<User> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.User WHERE id = :id");
        query.setParameter("id", id);
        User user = query.getSingleResult();

        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            user.setName(name);
            session.update(user);
            tr.commit();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }
}
