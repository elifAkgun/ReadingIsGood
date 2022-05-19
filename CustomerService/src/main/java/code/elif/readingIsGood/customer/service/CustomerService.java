package code.elif.readingIsGood.customer.service;


import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.ui.model.Customer;

public interface CustomerService {
    CustomerDTO getCustomerByNameAndPassword(String email, String password);

    CustomerDTO createCustomer(CustomerDTO customer);
}
