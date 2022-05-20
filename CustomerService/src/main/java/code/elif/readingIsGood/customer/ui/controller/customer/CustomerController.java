package code.elif.readingIsGood.customer.ui.controller.customer;

import code.elif.readingIsGood.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
public class CustomerController {
    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/info")
    public String authenticateCustomer(
            @RequestHeader(name = AUTHORIZATION, required = true)String jwt,
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "password", required = true) String password) {

        return  jwt;
    }


}
