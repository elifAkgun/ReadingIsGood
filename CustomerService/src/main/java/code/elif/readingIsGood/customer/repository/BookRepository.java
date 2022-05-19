package code.elif.readingIsGood.customer.repository;


import code.elif.readingIsGood.customer.repository.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
