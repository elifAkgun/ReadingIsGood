package code.elif.readingIsGood.customer.ui.model;


import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
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
