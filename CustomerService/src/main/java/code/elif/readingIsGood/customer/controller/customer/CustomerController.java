package code.elif.readingIsGood.customer.controller.customer;

import code.elif.readingIsGood.customer.model.Customer;
import code.elif.readingIsGood.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {
    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public Customer authenticateCustomer(@RequestParam(name = "email", required = true) String email,
                                         @RequestParam(name = "password", required = true) String password) {
        Customer customer
                = customerService.getCustomerByNameAndPassword(email, password);
        return customer;
    }

    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        return customer;
    }
}
