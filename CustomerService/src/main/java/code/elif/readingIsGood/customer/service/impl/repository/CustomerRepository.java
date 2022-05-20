package code.elif.readingIsGood.customer.service.impl.repository;

import code.elif.readingIsGood.customer.service.impl.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    public CustomerEntity findByEmail(String email);
}
