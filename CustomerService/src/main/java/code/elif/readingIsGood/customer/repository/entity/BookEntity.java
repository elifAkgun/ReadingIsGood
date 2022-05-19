package code.elif.readingIsGood.customer.repository.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "BOOK")
public class BookEntity {

    @Id
    @NonNull
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "amount")
    private Double amount;

}
