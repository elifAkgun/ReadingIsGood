package code.elif.readingIsGood.customer.ui.controller.order;

import code.elif.readingIsGood.customer.service.OrderService;
import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;
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
    public Page<OrderDTO> getCustomerOrder(
            @RequestParam(name = "customerId") Integer customerId, Pageable page) {
        return orderService.findOrdersByCustomerId(customerId,page);
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrderById(
            @PathVariable("id") Integer id) {
        return orderService.findOrderById(id);
    }
}
