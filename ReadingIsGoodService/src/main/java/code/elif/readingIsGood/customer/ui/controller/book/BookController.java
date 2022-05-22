package code.elif.readingIsGood.customer.ui.controller.book;


import code.elif.readingIsGood.customer.service.BookService;
import code.elif.readingIsGood.customer.service.dto.BookDTO;
import code.elif.readingIsGood.customer.ui.model.Book;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {

        ModelMapper modelMapper = new ModelMapper();
        //Strictly matches source and destination properties
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookDTO bookDTO = new BookDTO();
        modelMapper.map(book, bookDTO);

        Book createdBook = new Book();
        bookDTO = bookService.createBook(bookDTO);
        modelMapper.map(bookDTO, createdBook);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PostMapping("/{id}/stock")
    public ResponseEntity<Book> updateStock(@Valid @PathVariable("id") String id,
                                            @RequestParam("stock")
                                            @Min(value = 1, message = "Stock must greater than 0")
                                                    Integer stock) {

        BookDTO bookDTO = bookService.updateBookStock(id, stock);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Book updatedBook = new Book();
        modelMapper.map(bookDTO, updatedBook);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedBook);
    }
}
