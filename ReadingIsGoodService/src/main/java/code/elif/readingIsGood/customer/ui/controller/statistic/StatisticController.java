package code.elif.readingIsGood.customer.ui.controller.statistic;

import code.elif.readingIsGood.customer.service.StatisticService;
import code.elif.readingIsGood.customer.service.dto.StatisticDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    final
    StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public List<StatisticDTO> getMonthlyStatisticsForCustomer(
            @RequestParam(name = "customerId")
                   String customerId) {

        return statisticService.getMonthlyStatisticsForCustomer(customerId);
    }


}
