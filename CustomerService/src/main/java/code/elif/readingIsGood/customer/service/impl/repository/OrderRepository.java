package code.elif.readingIsGood.customer.service.impl.repository;


import code.elif.readingIsGood.customer.service.impl.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {

    public List<OrderEntity> getOrderById(Integer id);
}
