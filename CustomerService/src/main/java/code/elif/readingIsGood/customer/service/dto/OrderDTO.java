package code.elif.readingIsGood.customer.service.dto;


import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderDTO {

    @NonNull
    private Integer id;

    @NonNull
    private LocalDateTime date;

    @NonNull
    private CustomerDTO customer;

    @NonNull
    private List<BookEntity> books;

}
