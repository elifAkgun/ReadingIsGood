package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.model.Customer;
import code.elif.readingIsGood.customer.repository.dto.CustomerEntity;

public interface CustomerService {
    Customer getCustomerByNameAndPassword(String email, String password);

    Customer createCustomer(Customer customerEntity);
}
