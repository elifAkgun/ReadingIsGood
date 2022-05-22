package code.elif.readingIsGood.customer.service.repository;


import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, String> {
}
