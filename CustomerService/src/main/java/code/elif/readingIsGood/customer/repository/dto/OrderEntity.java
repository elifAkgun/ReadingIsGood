package code.elif.readingIsGood.customer.repository.dto;



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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Integer id;

    @NonNull
    private LocalDateTime date;

    @NonNull
    @Column(name="CUSTOMER_ID")
    private Integer customerId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = 	"ORDER_ITEM",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    private List<BookEntity> books;

}
