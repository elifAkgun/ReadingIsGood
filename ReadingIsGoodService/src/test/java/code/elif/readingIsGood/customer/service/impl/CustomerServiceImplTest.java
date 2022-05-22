package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void createCustomer() {
        CustomerEntity customerEntity = new CustomerEntity("1", "Marta", "May", "marta@marta.com", "123456");
        CustomerDTO customerDTO = new CustomerDTO("1", "Marta", "May", "marta@marta.com", "asdf");

        given(customerRepository.save(customerEntity)).willReturn(customerEntity);
        given(bCryptPasswordEncoder.encode("1234")).willReturn("asdf");

        CustomerDTO savedCustomer = customerService.createCustomer(customerDTO);

        assertEquals(customerDTO.getId(),savedCustomer.getId());
        assertEquals(customerDTO.getPassword(),savedCustomer.getPassword());
        assertEquals(customerDTO.getEmail(),savedCustomer.getEmail());
        assertEquals(customerDTO.getFirstName(),savedCustomer.getFirstName());
        assertEquals(customerDTO.getLastName(),savedCustomer.getLastName());
    }
}