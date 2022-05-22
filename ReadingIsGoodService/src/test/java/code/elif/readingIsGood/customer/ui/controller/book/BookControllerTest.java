package code.elif.readingIsGood.customer.ui.controller.book;

import code.elif.readingIsGood.customer.service.BookService;
import code.elif.readingIsGood.customer.service.dto.BookDTO;
import code.elif.readingIsGood.customer.ui.model.Book;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    private MockMvc mockMvc;

    @Test
    void getAllBooks() {
    }

    @Test
    void createBook() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        BookDTO bookDTO = new BookDTO("1", "LIFE OF APPLE", 10.2, 7);

        when(bookService.createBook(any(BookDTO.class))).thenReturn(bookDTO);

        Book book = new Book("1", "LIFE OF APPLE", 10.2, 7);

        ResponseEntity<Book> responseEntity = bookController.createBook(book);

        assertEquals(HttpStatus.CREATED.value(),responseEntity.getStatusCodeValue());
    }

    @Test
    void updateStock() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String bookId = "1";
        int stock = 10;
        BookDTO bookDTO = new BookDTO(bookId, "LIFE OF APPLE", 10.2, stock);

        when(bookService.updateBookStock(bookId, stock)).thenReturn(bookDTO);

        Book book = new Book(bookId, "LIFE OF APPLE", 10.2, stock);

        ResponseEntity<Book> responseEntity = bookController.updateStock(bookId,stock);

        assertEquals(HttpStatus.ACCEPTED.value(),responseEntity.getStatusCodeValue());
        assertEquals(book, responseEntity.getBody());

    }



    @Test
    void updateStock_negative_number() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        String bookId = "1";
        String stock = "-2";

        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController) // add ControllerAdvice to controller test
                .build();

        mockMvc.perform(post("/books").param("bookId", bookId)
                .param("stock", stock))
                .andExpect(status().isBadRequest()).andReturn();


    }
}


