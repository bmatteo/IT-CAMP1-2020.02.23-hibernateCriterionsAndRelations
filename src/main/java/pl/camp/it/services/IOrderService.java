package pl.camp.it.services;

import pl.camp.it.model.Order;

public interface IOrderService {
    void persistOrder(Order order);
    Order getOrderById(int id);
}
