package code.elif.readingIsGood.customer.repository;


import code.elif.readingIsGood.customer.repository.dto.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {

    public List<OrderEntity> getOrderById(Integer id);
}
