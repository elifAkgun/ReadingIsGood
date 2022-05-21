package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface OrderService {

    OrderDTO findOrderById(String id);

    Page<OrderDTO> findOrdersByCustomerId(String customerId, Pageable pageable);

    Page<OrderDTO> findOrdersByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    OrderDTO createOrder(OrderDTO orderDTO);
}
