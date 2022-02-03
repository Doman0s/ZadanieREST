package pl.edu.wszib.food.shop.database.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.food.shop.database.IOrderDAO;
import pl.edu.wszib.food.shop.model.Order;
import pl.edu.wszib.food.shop.model.User;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAO implements IOrderDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Order> getOrders() {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.Order");
        List<Order> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void addOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(order);
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
    public List<Order> getOrdersByUserId(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.Order WHERE user_id = :userId");
        query.setParameter("userId", userId);
        List<Order> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.Order WHERE id = :id");
        query.setParameter("id", id);
        try {
            Order order = query.getSingleResult();
            session.close();
            return Optional.of(order);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }
}
