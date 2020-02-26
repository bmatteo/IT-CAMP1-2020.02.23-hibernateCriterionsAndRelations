package pl.camp.it.dao;

import pl.camp.it.model.Order;

public interface IOrderDAO {
    void persistOrder(Order order);
    Order getOrderById(int id);
}
