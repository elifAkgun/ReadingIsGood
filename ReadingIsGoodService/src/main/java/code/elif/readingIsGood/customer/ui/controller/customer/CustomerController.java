package code.elif.readingIsGood.customer.ui.controller.customer;

import code.elif.readingIsGood.customer.service.CustomerService;
import code.elif.readingIsGood.customer.service.OrderService;
import code.elif.readingIsGood.customer.service.dto.CustomerDTO;
import code.elif.readingIsGood.customer.service.dto.OrderDTO;
import code.elif.readingIsGood.customer.ui.error.ApiError;
import code.elif.readingIsGood.customer.ui.model.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    final
    CustomerService customerService;

    final
    OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("{customerId}/order")
    public Page<OrderDTO> getCustomerOrder(
            @PathVariable(name = "customerId") String customerId, Pageable page) {
        return orderService.findOrdersByCustomerId(customerId,page);
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

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleValidationException(MethodArgumentNotValidException exception,
                                       HttpServletRequest request) {
        BindingResult bindingResult = exception.getBindingResult();

        Map<String, String> validationErrors = new HashMap<String, String>();

        for (FieldError fieldError: bindingResult.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ApiError apiError = new ApiError(400, "ValidationError", request.getServletPath());
        apiError.setValidationErrors(validationErrors);

        return apiError;
    }

}
