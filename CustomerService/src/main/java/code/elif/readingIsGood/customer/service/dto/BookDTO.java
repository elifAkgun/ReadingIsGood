package code.elif.readingIsGood.customer.service.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookDTO {

    @NonNull
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Double amount;

    @NonNull
    private Integer stock;
}
