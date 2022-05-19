package code.elif.readingIsGood.customer.ui.controller.order;

import code.elif.readingIsGood.customer.ui.model.Order;
import code.elif.readingIsGood.customer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public List<Order> getCustomerOrder(@RequestParam(name = "customerId") Integer customerId) {
        return orderService.findOrdersById(customerId);
    }
}
