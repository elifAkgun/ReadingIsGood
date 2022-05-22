package code.elif.readingIsGood.customer.ui.model;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private String id;

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @PositiveOrZero(message = "Amount information is not valid")
    @NotNull
    private Double amount;

    @NonNull
    @PositiveOrZero( message = "Stock information is not valid")
    @NotNull
    private Integer stock;
}
