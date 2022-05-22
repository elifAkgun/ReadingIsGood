package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.service.dto.BookDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO createBook(BookDTO bookDTO);

    @Transactional
    BookDTO updateBookStock(String bookId, Integer stock);
}
