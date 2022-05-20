package code.elif.readingIsGood.customer.service.impl;


import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.OrderRepository;
import code.elif.readingIsGood.customer.service.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.service.repository.entity.OrderEntity;
import code.elif.readingIsGood.customer.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public OrderDTO findOrderById(Integer id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        OrderDTO orderDTO = getOrderDTOFromEntity(orderEntity);
        orderDTO.setCustomer(getCustomerDTOFromEntity(orderEntity.getCustomer()));
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findOrdersByCustomerId(Integer customerId, Pageable pageable) {
        Page<OrderEntity> orderEntities = orderRepository.getOrderByCustomerId(customerId, pageable);
        Page<OrderDTO> orders = orderEntities
                .map((oe) -> {
                    OrderDTO orderDTOFromEntity = getOrderDTOFromEntity(oe);
                    orderDTOFromEntity.setCustomer(getCustomerDTOFromEntity(oe.getCustomer()));
                    return orderDTOFromEntity;
                });
        return orders;
    }

    private OrderEntity getOrderEntityFromOrderDTO(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(orderDTO, orderEntity);
        return orderEntity;
    }

    private OrderDTO getOrderDTOFromEntity(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(orderEntity, orderDTO);
        return orderDTO;
    }

    private CustomerDTO getCustomerDTOFromEntity(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(customerEntity, customerDTO);
        return customerDTO;
    }
}
