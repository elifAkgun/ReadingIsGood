package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.service.dto.CustomerDTO;

public interface CustomerService {

    CustomerDTO getCustomerByNameAndPassword(String email, String password);

    CustomerDTO createCustomer(CustomerDTO customer);
}
