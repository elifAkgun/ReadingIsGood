package code.elif.readingIsGood.customer.service.dto;


import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String id;


    private LocalDateTime date;

    @NonNull
    private CustomerDTO customer;

    @NonNull
    private List<BookDTO> books;

}
