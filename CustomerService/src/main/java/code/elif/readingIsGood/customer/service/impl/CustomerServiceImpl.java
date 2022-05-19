package code.elif.readingIsGood.customer.service.impl;


import code.elif.readingIsGood.customer.model.Customer;
import code.elif.readingIsGood.customer.repository.CustomerRepository;
import code.elif.readingIsGood.customer.repository.OrderRepository;
import code.elif.readingIsGood.customer.repository.dto.CustomerEntity;
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
    public Customer getCustomerByNameAndPassword(String email, String password) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email);
        Customer customer = Customer.map(customerEntity);
        return  customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
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
