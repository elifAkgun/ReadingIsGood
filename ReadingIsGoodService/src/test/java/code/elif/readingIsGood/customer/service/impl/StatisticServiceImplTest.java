package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.dto.StatisticDTO;
import code.elif.readingIsGood.customer.service.repository.StatisticRepository;
import code.elif.readingIsGood.customer.service.repository.entity.StatisticEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class StatisticServiceImplTest {

    @Mock
    StatisticRepository statisticRepository;

    @InjectMocks
    StatisticServiceImpl statisticService;

    @Test
    void getMonthlyStatisticsForCustomer() {

        List<StatisticEntity> statisticEntities = Arrays.asList(
                new StatisticEntity(1,2021,5,1,3.10,"1"),
                new StatisticEntity(2,2021,5,1,3.10,"1"),
                new StatisticEntity(3,2021,5,1,3.10,"1")
        );

        given(statisticRepository.findByCustomerId("1"))
                .willReturn(statisticEntities);

        List<StatisticDTO> statisticDTO = Arrays.asList(
                new StatisticDTO(2021,5,1,3.10),
                new StatisticDTO(2021,5,1,3.10),
                new StatisticDTO(2021,5,1,3.10)
        );



        List<StatisticDTO> result = statisticService.getMonthlyStatisticsForCustomer("1");

        assertEquals(statisticDTO,result);


    }
}