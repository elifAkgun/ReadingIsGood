package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.dto.BookDTO;
import code.elif.readingIsGood.customer.service.repository.BookRepository;
import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import org.hibernate.StaleStateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void getAllBooks() {

        List<BookDTO> books = Arrays.asList(
                new BookDTO("1","LIFE OF APPLE",10.2,7),
                new BookDTO("1","LIFE OF KIWI",20.3,5),
                new BookDTO("1","LIFE OF PINEAPPLE",30.3,10)
        );

        List<BookEntity> bookEntities = Arrays.asList(
                new BookEntity("1","LIFE OF APPLE",10.2,7,1),
                new BookEntity("1","LIFE OF KIWI",20.3,5,1),
                new BookEntity("1","LIFE OF PINEAPPLE",30.3,10,1)
        );

        //given
        given(bookRepository.findAll()).willReturn(bookEntities);

        //when
        List<BookDTO> bookReturn = bookService.getAllBooks();

        //then
        assertEquals(books, bookReturn);
    }

    @Test
    void createBook() {
        BookEntity bookEntity = new BookEntity("1", "LIFE OF APPLE", 10.2, 7, 1);
        BookDTO bookDTO = new BookDTO("1", "LIFE OF APPLE", 10.2, 7);

        given(bookRepository.save(bookEntity)).willReturn(bookEntity);

        BookDTO savedBook = bookService.createBook(bookDTO);

        assertEquals(bookDTO,savedBook);

    }

    @Test
    void createBook_throwsDataIntegrityViolationException() {
//        BookEntity bookEntity = new BookEntity("1", "LIFE OF APPLE", 10.2, 7, 1);
//        BookDTO bookDTO = new BookDTO("1", "LIFE OF APPLE", 10.2, 7);
//
//        when(bookRepository.save(bookEntity))
//                .thenThrow(StaleStateException.class);
//
//        assertThrows(DataIntegrityViolationException.class, () -> bookService.createBook(bookDTO));

    }

    @Test
    void updateBookStock() {
        BookEntity bookEntity = new BookEntity("1", "LIFE OF APPLE", 10.2, 7, 1);
        BookEntity expectedEntity = new BookEntity("1", "LIFE OF APPLE", 10.2, 10, 1);

        given(bookRepository.findById("1")).willReturn(java.util.Optional.of(bookEntity));
        given(bookRepository.save(bookEntity)).willReturn(expectedEntity);

        BookDTO bookDTO = bookService.updateBookStock("1", 10);

        assertEquals(10,bookDTO.getStock());
    }
}