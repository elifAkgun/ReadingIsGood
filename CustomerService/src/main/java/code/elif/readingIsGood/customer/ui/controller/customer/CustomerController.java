package code.elif.readingIsGood.customer.ui.controller.customer;

import code.elif.readingIsGood.customer.service.CustomerService;
import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.ui.model.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        ModelMapper modelMapper = new ModelMapper();
        //Strictly matches source and destination properties
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CustomerDTO customerDTO
                = customerService.getCustomerByNameAndPassword(email, password);
        Customer customer = new Customer();

        modelMapper.map(customerDTO, customer);


        return customer;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        //Strictly matches source and destination properties
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CustomerDTO customerDTO = new CustomerDTO();
        modelMapper.map(customer, customerDTO);

        customerDTO = customerService.createCustomer(customerDTO);
        modelMapper.map(customerDTO, customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
