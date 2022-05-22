package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.BookService;
import code.elif.readingIsGood.customer.service.dto.BookDTO;
import code.elif.readingIsGood.customer.service.repository.BookRepository;
import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import org.hibernate.StaleStateException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {


    BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {

        List<BookEntity> bookEntities = bookRepository.findAll();

        List<BookDTO> books = new ArrayList<>();
        if (bookEntities != null) {
            bookEntities.stream().forEach(b -> {
                books.add(new BookDTO(b.getId(), b.getName(), b.getAmount(), b.getStock()));
            });
        }
        return books;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        bookDTO.setId(UUID.randomUUID().toString());
        BookEntity bookEntity = getBookEntityFromBookDTO(bookDTO);
        bookRepository.save(bookEntity);
        return bookDTO;
    }

    @Override
    @Transactional
    public BookDTO updateBookStock(String bookId, Integer stock) {
        BookEntity persistedBook = bookRepository.findById(bookId).get();
        persistedBook.setStock(stock);
        BookEntity updatedBook = save(persistedBook);
        BookDTO bookDTO = getBookDTOFromEntity(updatedBook);
        return bookDTO;
    }

    private BookEntity save(BookEntity bookEntity) {
        try {
            bookEntity = bookRepository.save(bookEntity);
        } catch (StaleStateException stateException) {
            throw new DataIntegrityViolationException("Stock information was not updated. Because stock is changed by another process.");
        }
        return bookEntity;
    }

    private BookEntity getBookEntityFromBookDTO(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(bookDTO, bookEntity);
        return bookEntity;
    }

    private BookDTO getBookDTOFromEntity(BookEntity updatedBook) {
        BookDTO bookDTO = new BookDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(updatedBook, bookDTO);
        return bookDTO;
    }

}
