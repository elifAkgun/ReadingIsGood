package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import code.elif.readingIsGood.customer.ui.model.Order;

import java.util.List;

public interface OrderService {

    OrderDTO findOrderById(Integer id);

    List<OrderDTO> findOrdersByCustomerId(Integer customerId);
}
