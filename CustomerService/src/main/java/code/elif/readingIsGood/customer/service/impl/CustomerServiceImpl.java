package code.elif.readingIsGood.customer.service.impl;


import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.ui.model.Customer;
import code.elif.readingIsGood.customer.repository.CustomerRepository;
import code.elif.readingIsGood.customer.repository.OrderRepository;
import code.elif.readingIsGood.customer.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    OrderRepository orderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public CustomerDTO getCustomerByNameAndPassword(String email, String password) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email);
        CustomerDTO customer = new CustomerDTO();

        ModelMapper modelMapper = new ModelMapper();
        //Strictly matches source and destination properties
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.map(customerEntity,customer);
        return  customer;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        customer.setId(UUID.randomUUID().toString().hashCode());

        ModelMapper modelMapper = new ModelMapper();
        //Strictly matches source and destination properties
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CustomerEntity customerEntity = new CustomerEntity();
        modelMapper.map(customer,customerEntity);

        customerRepository.save(customerEntity);

        return customer;
    }
}
