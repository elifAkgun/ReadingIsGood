package code.elif.readingIsGood.customer.service.impl;


import code.elif.readingIsGood.customer.service.OrderService;
import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import code.elif.readingIsGood.customer.service.repository.BookRepository;
import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.OrderRepository;
import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import code.elif.readingIsGood.customer.service.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.service.repository.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    BookRepository bookRepository;

    CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderDTO findOrderById(String id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        OrderDTO orderDTO = getOrderDTOFromEntity(orderEntity);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findOrdersByCustomerId(String customerId, Pageable pageable) {
        Page<OrderEntity> orderEntities = orderRepository.getOrderByCustomerId(customerId, pageable);
        Page<OrderDTO> orders = orderEntities
                .map((oe) -> {
                    OrderDTO orderDTOFromEntity = getOrderDTOFromEntity(oe);
                    return orderDTOFromEntity;
                });
        return orders;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = getOrderEntityFromOrderDTO(orderDTO);

        CustomerEntity customerEntity = customerRepository.findById(orderDTO.getCustomer().getId()).get();

        if(customerEntity==null){
            throw new DataIntegrityViolationException("Customer is not exist with given id");
        }

        orderEntity.setCustomer(customerEntity);

        List<BookEntity> bookEntities = bookRepository.findAllById(
                orderDTO.getBooks()
                        .stream().map((b) -> b.getId())
                        .collect(Collectors.toList()));

        AtomicReference<Double> totalOrderAmount = new AtomicReference<>(0.0);

        bookEntities.stream().forEach((b) -> {
            if (b == null) {
                throw new DataIntegrityViolationException("Book is not exist with given id");
            }

            if(b.getStock() >0) {
                b.setStock(b.getStock() - 1);
                totalOrderAmount.updateAndGet(oA -> oA + b.getAmount());
            }
            else{
                throw new DataIntegrityViolationException(b.getName() + " is out of stock... Please try later.");
            }
        });

        orderEntity.setBooks(bookEntities);
        orderEntity.setId(UUID.randomUUID().toString());
        orderEntity.setDate(LocalDateTime.now());
        orderEntity.setAmount(totalOrderAmount.get());
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return getOrderDTOFromEntity(savedOrderEntity);
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
}
