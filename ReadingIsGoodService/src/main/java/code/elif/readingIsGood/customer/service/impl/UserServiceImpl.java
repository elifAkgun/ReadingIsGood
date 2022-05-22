package code.elif.readingIsGood.customer.service.impl;

import code.elif.readingIsGood.customer.service.repository.CustomerRepository;
import code.elif.readingIsGood.customer.service.repository.entity.CustomerEntity;
import code.elif.readingIsGood.customer.service.UserService;
import code.elif.readingIsGood.customer.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    final
    CustomerRepository customerRepository;

    public UserServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerEntity customerEntity = customerRepository.findByEmail(username);
        if(customerEntity == null) throw new UsernameNotFoundException(username);

        return new User(customerEntity.getEmail(), customerEntity.getPassword(), true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDTO getUserDetailsByEmail(String userName) {
        UserDTO userDTO = null;
        CustomerEntity customerEntity = customerRepository.findByEmail(userName);
        ModelMapper modelMapper = new ModelMapper();
        if(customerEntity!=null) {
            userDTO = new UserDTO();
            modelMapper.map(customerEntity, userDTO);
        }
        return userDTO;
    }
}
