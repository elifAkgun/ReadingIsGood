package code.elif.readingIsGood.customer.ui.controller.order;

import code.elif.readingIsGood.customer.service.OrderService;
import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    final
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public List<OrderDTO> getCustomerOrder(
            @RequestParam(name = "customerId") Integer customerId) {
        return orderService.findOrdersByCustomerId(customerId);
    }
}
