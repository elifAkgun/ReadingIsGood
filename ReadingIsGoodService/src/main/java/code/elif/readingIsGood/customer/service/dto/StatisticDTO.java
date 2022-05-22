package code.elif.readingIsGood.customer.service.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class StatisticDTO {

    Integer orderYear;

    Integer orderMonth;

    Integer totalOrderCount;

    Double totalPurchasedAmount;

}
