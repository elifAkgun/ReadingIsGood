package code.elif.readingIsGood.customer.ui.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Book {

    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Double amount;

}
