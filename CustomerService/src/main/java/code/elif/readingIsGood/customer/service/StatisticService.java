package code.elif.readingIsGood.customer.service;

import code.elif.readingIsGood.customer.service.dto.StatisticDTO;

import java.util.List;

public interface StatisticService {

    List<StatisticDTO> getMonthlyStatisticsForCustomer(String customerId);
}
