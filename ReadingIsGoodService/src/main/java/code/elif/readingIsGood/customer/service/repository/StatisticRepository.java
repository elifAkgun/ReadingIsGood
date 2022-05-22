package code.elif.readingIsGood.customer.service.repository;

import code.elif.readingIsGood.customer.service.repository.entity.StatisticEntity;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticRepository extends  JpaRepository<StatisticEntity,Integer>{

      public List<StatisticEntity> findByCustomerId(String customerId);
}
