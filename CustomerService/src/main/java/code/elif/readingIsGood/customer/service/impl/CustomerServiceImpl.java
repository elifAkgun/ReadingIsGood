package code.elif.readingIsGood.customer.service.impl;


import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.OrderRepository;
import code.elif.readingIsGood.customer.service.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.service.CustomerService;
import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    OrderRepository orderRepository;
    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        customer.setId(UUID.randomUUID().toString().hashCode());
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        //Strictly matches source and destination properties
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CustomerEntity customerEntity = new CustomerEntity();
        modelMapper.map(customer,customerEntity);

        customerRepository.save(customerEntity);

        return customer;
    }


}
