package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.StatisticService;
import code.elif.readingIsGood.customer.service.repository.entity.StatisticEntity;
import code.elif.readingIsGood.customer.service.repository.entity.StatisticRepository;
import code.elif.readingIsGood.customer.service.dto.StatisticDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {


    StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }


    @Override
    public List<StatisticDTO> getMonthlyStatisticsForCustomer(String customerId) {

        List<StatisticEntity> statisticList = statisticRepository.findByCustomerId(customerId);

        List<StatisticDTO> monthStatistics = new ArrayList<>();
        statisticList.stream().forEach( se ->
                monthStatistics.add(
                    new StatisticDTO(se.getOrderYear(),
                    se.getOrderMonth(),
                    se.getTotalOrderCount(),
                    se.getTotalPurchasedAmount()
                )));

        return monthStatistics;
    }
}
