package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.impl.repository.BookRepository;
import code.elif.readingIsGood.customer.service.impl.repository.entity.BookEntity;
import code.elif.readingIsGood.customer.service.BookService;
import code.elif.readingIsGood.customer.ui.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


    BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks(){

        List<BookEntity> bookEntities = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
       // bookEntities.stream().forEach(bookEntity -> books.add(Book.map(bookEntity)));

        return books;
    }

}
