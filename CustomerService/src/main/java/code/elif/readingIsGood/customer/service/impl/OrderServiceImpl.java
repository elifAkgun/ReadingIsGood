package code.elif.readingIsGood.customer.service.impl;


import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.OrderRepository;
import code.elif.readingIsGood.customer.service.repository.entity.OrderEntity;
import code.elif.readingIsGood.customer.service.OrderService;
import code.elif.readingIsGood.customer.ui.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Order> findOrdersById(Integer id) {
        List<OrderEntity> orderEntities = orderRepository.getOrderById(id);
        List<Order> orders = new ArrayList<>();
        orderEntities.stream().forEach(orderEntity -> orders.add(map(orderEntity)));
        return orders;
    }

    public Order map(OrderEntity orderEntity) {

        return null;
    }
}
