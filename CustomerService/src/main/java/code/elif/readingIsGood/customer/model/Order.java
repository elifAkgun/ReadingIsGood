package code.elif.readingIsGood.customer.model;


import code.elif.readingIsGood.customer.repository.dto.BookEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Order {

    @NonNull
    private Integer id;

    @NonNull
    private LocalDateTime date;

    @NonNull
    private Customer customer;

    private List<BookEntity> books;

}
