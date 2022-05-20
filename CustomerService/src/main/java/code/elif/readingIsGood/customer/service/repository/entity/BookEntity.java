package code.elif.readingIsGood.customer.service.repository.entity;

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
    @Column(name = "NAME")
    private String name;

    @NonNull
    @Column(name = "AMOUNT")
    private Double amount;

    @NonNull
    @Column(name = "STOCK")
    private Integer stock;

    @Version
    @Column(name="VERSION")
    private Integer version;


}
