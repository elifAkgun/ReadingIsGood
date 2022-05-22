package code.elif.readingIsGood.customer.service.repository.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "STATISTIC_VIEW")
@Entity
@Immutable
@AllArgsConstructor
@EqualsAndHashCode
public class StatisticEntity {

    @Id
    Integer id;

    @Column(name="order_year")
    Integer orderYear;

    @Column(name="order_month")
    Integer orderMonth;

    @Column(name = "total_order_count")
    Integer totalOrderCount;

    @Column(name = "total_purchased_amount")
    Double totalPurchasedAmount;

    @Column(name = "customer_id")
    String customerId;
}
