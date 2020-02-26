package pl.camp.it.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.model.Order;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistOrder(Order order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(order);
        session.getTransaction().commit();
    }

    @Override
    public Order getOrderById(int id) {
        Session session = sessionFactory.openSession();
        return session
                .createQuery("FROM pl.camp.it.model.Order WHERE id = " + id, Order.class)
                .uniqueResult();
    }
}
