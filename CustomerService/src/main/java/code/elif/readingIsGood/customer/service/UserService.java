package code.elif.readingIsGood.customer.service;

import code.elif.readingIsGood.customer.repository.CustomerRepository;
import code.elif.readingIsGood.customer.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public interface UserService extends UserDetailsService {


    UserDTO getUserDetailsByEmail(String userName);
}
