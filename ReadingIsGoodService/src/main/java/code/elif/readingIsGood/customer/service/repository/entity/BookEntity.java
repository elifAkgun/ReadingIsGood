package code.elif.readingIsGood.customer.service.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "BOOK")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookEntity {

    @Id
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "STOCK")
    private Integer stock;

    @Version
    @Column(name="VERSION")
    private Integer version;

}
