package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IOrderDAO;
import pl.camp.it.model.Order;
import pl.camp.it.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderDAO orderDAO;

    @Override
    public void persistOrder(Order order) {
        orderDAO.persistOrder(order);
    }

    @Override
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }
}
