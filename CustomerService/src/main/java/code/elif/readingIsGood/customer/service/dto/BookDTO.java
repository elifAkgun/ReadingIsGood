package code.elif.readingIsGood.customer.service.dto;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {

    private String id;

    private String name;

    @PositiveOrZero(message = "Amount must have positive or zero")
    private Double amount;

    @PositiveOrZero(message = "Stock must have positive or zero")
    private Integer stock;

}
