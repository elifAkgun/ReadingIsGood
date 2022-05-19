package code.elif.readingIsGood.customer.repository;


import code.elif.readingIsGood.customer.repository.dto.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
