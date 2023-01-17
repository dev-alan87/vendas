package io.github.dev_alan87.sales.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.dev_alan87.sales.domain.entity.MyUser;
import io.github.dev_alan87.sales.domain.respository.UserRepository;
import io.github.dev_alan87.sales.exception.InvalidPasswordException;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Transactional
    public MyUser save(MyUser user) {
        return repository.save(user);
    }
    
    public UserDetails authenticate(MyUser user) {
        UserDetails userDetails =
                loadUserByUsername(user.getUsername());
        if(encoder.matches(
                user.getPassword(), 
                userDetails.getPassword())) {
            return userDetails;
        }
        throw new InvalidPasswordException();
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found in database"));
        
        String[] roles = user.isAdmin() ? 
                new String[] {"ADMIN", "USER"} :
                new String[] {"USER"};
        
        return User
                .builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(roles)
                .build();
    }

}
