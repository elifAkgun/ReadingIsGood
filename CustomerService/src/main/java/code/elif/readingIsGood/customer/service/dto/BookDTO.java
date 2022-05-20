package code.elif.readingIsGood.customer.service.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {


    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Double amount;

    @NonNull
    private Integer stock;

}
