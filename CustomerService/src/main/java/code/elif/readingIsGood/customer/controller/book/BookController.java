package code.elif.readingIsGood.customer.controller.book;


import code.elif.readingIsGood.customer.model.Book;
import code.elif.readingIsGood.customer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(@RequestParam(name="name", required=false, defaultValue="World")
                                       String name) {
        return bookService.getAllBooks();
    }
}
