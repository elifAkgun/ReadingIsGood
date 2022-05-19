package code.elif.readingIsGood.customer.ui.controller.user;

import code.elif.readingIsGood.customer.service.CustomerService;
import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.ui.model.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/user")
public class UserController {

    final
    CustomerService customerService;


    @Autowired
    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        //Strictly matches source and destination properties
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        CustomerDTO customerDTO = new CustomerDTO();
        modelMapper.map(customer, customerDTO);

        Customer customerResp = new Customer();
        customerDTO = customerService.createCustomer(customerDTO);
        modelMapper.map(customerDTO, customerResp);
        customerResp.setPassword(customer.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResp);
    }

}
