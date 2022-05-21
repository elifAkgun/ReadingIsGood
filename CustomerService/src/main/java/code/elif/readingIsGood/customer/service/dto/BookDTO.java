package code.elif.readingIsGood.customer.service.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {


    private String id;


    private String name;


    private Double amount;


    private Integer stock;

}
