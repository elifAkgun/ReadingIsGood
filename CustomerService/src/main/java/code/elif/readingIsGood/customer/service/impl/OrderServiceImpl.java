package code.elif.readingIsGood.customer.service.impl;


import code.elif.readingIsGood.customer.ui.model.Customer;
import code.elif.readingIsGood.customer.ui.model.Order;
import code.elif.readingIsGood.customer.repository.CustomerRepository;
import code.elif.readingIsGood.customer.repository.OrderRepository;
import code.elif.readingIsGood.customer.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.repository.entity.OrderEntity;
import code.elif.readingIsGood.customer.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
