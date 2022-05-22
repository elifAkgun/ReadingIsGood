package code.elif.readingIsGood.customer.service.dto;


import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDTO implements Serializable {

    private String id;

    private LocalDateTime date;

    private CustomerDTO customer;

    private List<BookDTO> books;

    private Integer bookCount;

    private Double amount;

}
