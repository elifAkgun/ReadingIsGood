package code.elif.readingIsGood.customer.service;

import code.elif.readingIsGood.customer.service.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO getUserDetailsByEmail(String userName);
}
