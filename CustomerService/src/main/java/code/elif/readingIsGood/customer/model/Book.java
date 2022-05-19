package code.elif.readingIsGood.customer.model;

import code.elif.readingIsGood.customer.repository.dto.BookEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Book {

    @NonNull
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Double amount;

    public static Book map(BookEntity bookEntity) {
        return new Book(bookEntity.getId(),
                bookEntity.getName(),
                bookEntity.getAmount());
    }
}
