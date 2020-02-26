package pl.camp.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.model.Order;
import pl.camp.it.model.Product;
import pl.camp.it.model.User;
import pl.camp.it.services.IOrderService;
import pl.camp.it.services.IUserService;

import java.time.LocalDate;

@Controller
public class SimpleController {

    @Autowired
    IUserService userService;

    @Autowired
    IOrderService orderService;

    @GetMapping(value = "/u")
    public String u() {
        User user = new User();
        user.setLogin("mateusz");
        user.setPass("mateusz");

        userService.persistUser(user);

        return "page";
    }

    @RequestMapping(value = "/gu", method = RequestMethod.GET)
    public String gu() {
        User user = userService.getUserById(1);

        System.out.println(user);

        System.out.println(user.getOrders());

        for (Order o : user.getOrders()) {
            System.out.println(o.getProducts());
        }

        return "page";
    }

    @GetMapping("o")
    public String o() {
        Order order = new Order();

        order.setDate(LocalDate.now());

        Product p1 = new Product();
        p1.setName("Woda");
        p1.setPrice(1.69);
        p1.getOrders().add(order);

        order.getProducts().add(p1);

        Product p2 = new Product();
        p2.setName("Papier");
        p2.setPrice(5.00);
        p2.getOrders().add(order);

        order.getProducts().add(p2);

        User user = userService.getUserById(1);

        user.getOrders().add(order);
        order.setUser(user);

        userService.persistUser(user);

        return "page";
    }

    @GetMapping(value = "/go")
    public String go() {
        Order order = orderService.getOrderById(1);

        System.out.println(order);

        return "page";
    }
}
