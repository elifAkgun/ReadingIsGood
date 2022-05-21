package code.elif.readingIsGood.customer.service.repository.entity;



import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    private String id;


    private LocalDateTime date;


    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = 	"ORDER_ITEM",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    private List<BookEntity> books;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BOOK_QUANTITY")
    private Integer bookCount;

}
