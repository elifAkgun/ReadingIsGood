package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.dto.BookDTO;
import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import code.elif.readingIsGood.customer.service.repository.BookRepository;
import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.OrderRepository;
import code.elif.readingIsGood.customer.service.repository.entity.BookEntity;
import code.elif.readingIsGood.customer.service.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.service.repository.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    BookRepository bookRepository;




    @Test
    void findOrderById() {

        List<BookEntity> bookEntities = Arrays.asList(
                new BookEntity("1", "LIFE OF APPLE", 10.2, 7, 1),
                new BookEntity("1", "LIFE OF KIWI", 20.3, 5, 1),
                new BookEntity("1", "LIFE OF PINEAPPLE", 30.3, 10, 1)
        );

        LocalDateTime dateTime = LocalDateTime.of(2022, 2, 2, 2, 2);


        CustomerEntity customerEntity = new CustomerEntity("1", "Marta", "May", "marta@marta.com", "asdf");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setBookCount(bookEntities.size());
        orderEntity.setCustomer(customerEntity);
        orderEntity.setAmount(bookEntities.stream().mapToDouble(BookEntity::getAmount).sum());
        orderEntity.setBooks(bookEntities);
        orderEntity.setDate(dateTime);
        orderEntity.setId("1");

        CustomerDTO customerDTO = new CustomerDTO("1", "Marta", "May", "marta@marta.com", "asdf");

        List<BookDTO> bookDTOs = Arrays.asList(
                new BookDTO("1", "LIFE OF APPLE", 10.2, 7),
                new BookDTO("1", "LIFE OF KIWI", 20.3, 5),
                new BookDTO("1", "LIFE OF PINEAPPLE", 30.3, 10)
        );

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(customerDTO);
        orderDTO.setBooks(bookDTOs);
        orderDTO.setDate(dateTime);
        orderDTO.setId("1");
        orderDTO.setAmount(bookDTOs.stream().mapToDouble(BookDTO::getAmount).sum());
        orderDTO.setBookCount(bookDTOs.size());

        given(orderRepository.findById("1"))
                .willReturn(java.util.Optional.of(orderEntity));

        OrderDTO foundOrderDTO = orderService.findOrderById("1");

        assertEquals(orderDTO, foundOrderDTO);

    }

    @Test
    void findOrdersByCustomerId() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 2, 2, 2, 2);

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setDate(dateTime);
        orderEntity.setId("1");

        CustomerDTO customerDTO = new CustomerDTO("1", "Marta", "May", "marta@marta.com", "asdf");

        List<BookDTO> bookDTOs = Arrays.asList(
                new BookDTO("1", "LIFE OF APPLE", 10.2, 7),
                new BookDTO("1", "LIFE OF KIWI", 20.3, 5),
                new BookDTO("1", "LIFE OF PINEAPPLE", 30.3, 10)
        );

        List<OrderEntity> orderEntities = Arrays.asList(orderEntity);

        PageImpl<OrderEntity> orderEntitiesPage =
                new PageImpl<>(orderEntities,
                        PageRequest.of(1, 10),
                        orderEntities.size());


        given(orderRepository.getOrderByCustomerId("1", Pageable.ofSize(1)))
                .willReturn(orderEntitiesPage);


        Page<OrderDTO> ordersByCustomerId = orderService.findOrdersByCustomerId("1", Pageable.ofSize(1));

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDate(dateTime);
        orderDTO.setId("1");


        OrderDTO actual = ordersByCustomerId.getContent().get(0);
        assertEquals(orderDTO, actual);
    }

    @Test
    void findOrdersByDateBetween() {

        LocalDate date = LocalDate.of(2022, 2, 2);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(
                LocalDateTime.of(2022, 2,
                        2, 2, 2));
        orderEntity.setId("1");


        List<OrderEntity> orderEntities = Arrays.asList(orderEntity);

        PageImpl<OrderEntity> orderEntitiesPage =
                new PageImpl<>(orderEntities,
                        PageRequest.of(1, 10),
                        orderEntities.size());


        given(orderRepository.getOrderEntitiesByDateBetween(
                LocalDateTime.of(date, LocalTime.MIN),
                LocalDateTime.of(date,LocalTime.MAX),
                Pageable.ofSize(1)))
                .willReturn(orderEntitiesPage);



        Page<OrderDTO> ordersByCustomerId =
                orderService.findOrdersByDateBetween(
                        date,
                        date,
                        Pageable.ofSize(1));

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDate(LocalDateTime.of(2022, 2, 2, 2, 2));
        orderDTO.setId("1");

        OrderDTO actual = ordersByCustomerId.getContent().get(0);
        assertEquals(orderDTO, actual);
    }

    @Test
    void createOrder() {

        Instant fixedTime =
                LocalDate.of(2020, 1, 1).atStartOfDay(ZoneId.systemDefault())
                        .toInstant();

        Clock fixedClock = Clock.fixed(fixedTime, ZoneId.systemDefault());

        orderService = new OrderServiceImpl(orderRepository,bookRepository,customerRepository,fixedClock);

        List<BookEntity> bookEntities = Arrays.asList(
                new BookEntity("1", "LIFE OF APPLE", 10.2, 7, 1),
                new BookEntity("2", "LIFE OF KIWI", 20.3, 5, 1),
                new BookEntity("3", "LIFE OF PINEAPPLE", 30.3, 10, 1)
        );



        CustomerEntity customerEntity = new CustomerEntity("1", "Marta", "May", "marta@marta.com", "asdf");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setBookCount(bookEntities.size());
        orderEntity.setCustomer(customerEntity);
        orderEntity.setAmount(bookEntities.stream().mapToDouble(BookEntity::getAmount).sum());
        orderEntity.setBooks(bookEntities);
        orderEntity.setId("1");
        orderEntity.setDate(LocalDateTime.now(fixedClock));

        given(customerRepository.findById(customerEntity.getId()))
                .willReturn(java.util.Optional.of(customerEntity));

        given(bookRepository.findAllById(Arrays.asList("1","2","3")))
                .willReturn(bookEntities);

        given(orderRepository.save(orderEntity)).willReturn(orderEntity);

        OrderDTO givenOrderDTO = getOrderDTOFromEntity(orderEntity);

        OrderDTO createdOrderDTO = orderService.createOrder(givenOrderDTO);

        assertEquals(givenOrderDTO,createdOrderDTO);
    }


    private OrderDTO getOrderDTOFromEntity(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(orderEntity, orderDTO);
        return orderDTO;
    }
}