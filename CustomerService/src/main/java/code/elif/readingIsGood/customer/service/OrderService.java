package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.ui.model.Order;

import java.util.List;

public interface OrderService {
    public List<Order> findOrdersById(Integer id);
}
