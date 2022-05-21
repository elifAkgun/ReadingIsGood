package code.elif.readingIsGood.customer.ui.controller.order;

import code.elif.readingIsGood.customer.service.OrderService;
import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import code.elif.readingIsGood.customer.service.repository.entity.OrderEntity;
import code.elif.readingIsGood.customer.ui.model.Order;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    final
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<OrderDTO> getCustomerOrder(
            @RequestParam(name = "customerId") String customerId, Pageable page) {
        return orderService.findOrdersByCustomerId(customerId,page);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(
            @PathVariable("id") String id) {
        return orderService.findOrderById(id);
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody Order order){
        OrderDTO orderDTO = getOrderDTO(order);
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return createdOrder;
    }

    private OrderDTO getOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(order, orderDTO);
        return orderDTO;
    }
}
