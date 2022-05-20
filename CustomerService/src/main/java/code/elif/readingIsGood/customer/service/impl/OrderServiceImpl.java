package code.elif.readingIsGood.customer.service.impl;


import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.OrderRepository;
import code.elif.readingIsGood.customer.service.repository.entity.OrderEntity;
import code.elif.readingIsGood.customer.service.OrderService;
import code.elif.readingIsGood.customer.ui.model.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

        return orderDTO;
    }

    @Override
    public List<OrderDTO> findOrdersByCustomerId(Integer customerId) {
        List<OrderEntity> orderEntities = orderRepository.getOrderByCustomerId(customerId);
        List<OrderDTO> orders = new ArrayList<>();
        orderEntities.stream().forEach(orderEntity -> orders.add(getOrderDTOFromEntity(orderEntity)));
        return orders;
    }

    private OrderEntity getOrderEntityFromBookDTO(OrderDTO orderDTO) {
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
}
