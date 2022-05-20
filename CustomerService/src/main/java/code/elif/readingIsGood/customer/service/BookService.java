package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.service.dto.BookDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBook(Integer bookId);

    BookDTO createBook(BookDTO bookDTO);

    @Transactional
    BookDTO decrementBookStock(Integer bookId, Integer increment);

    @Transactional
    BookDTO updateBookStock(Integer bookId, Integer stock);
}
