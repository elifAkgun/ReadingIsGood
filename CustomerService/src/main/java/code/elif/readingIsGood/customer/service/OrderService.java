package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import code.elif.readingIsGood.customer.ui.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderDTO findOrderById(Integer id);

    Page<OrderDTO> findOrdersByCustomerId(Integer customerId, Pageable pageable);
}
