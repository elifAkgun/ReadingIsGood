package code.elif.readingIsGood.customer.service.impl.repository;


import code.elif.readingIsGood.customer.service.impl.repository.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
