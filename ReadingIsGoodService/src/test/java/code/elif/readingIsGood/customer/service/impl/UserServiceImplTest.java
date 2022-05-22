package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.service.dto.UserDTO;
import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.ui.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void getUserDetailsByEmail() {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("marta@marta.com");
        userDTO.setPassword("123456");

        CustomerEntity customerEntity = new CustomerEntity("1", "Marta", "May", "marta@marta.com", "123456");

        when(customerRepository.findByEmail(userDTO.getEmail()))
                .thenReturn(customerEntity);

        UserDTO userDetailsByEmail = userService.getUserDetailsByEmail(userDTO.getEmail());

        assertEquals(userDTO,userDetailsByEmail);

    }
}