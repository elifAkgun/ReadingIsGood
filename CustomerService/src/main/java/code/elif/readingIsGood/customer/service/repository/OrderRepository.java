package code.elif.readingIsGood.customer.service.repository;


import code.elif.readingIsGood.customer.service.repository.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    Page<OrderEntity> getOrderByCustomerId(String id, Pageable pageable);
}
