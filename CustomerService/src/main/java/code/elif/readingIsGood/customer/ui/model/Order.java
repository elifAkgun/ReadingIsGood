package code.elif.readingIsGood.customer.ui.model;


import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;

    private LocalDateTime date;

    @NonNull
    private Customer customer;

    @NonNull
    private List<Book> books;

}
