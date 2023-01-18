package io.github.dev_alan87.sales.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dev_alan87.sales.api.controller.dto.CredentialsDTO;
import io.github.dev_alan87.sales.api.controller.dto.TokenDTO;
import io.github.dev_alan87.sales.domain.entity.MyUser;
import io.github.dev_alan87.sales.exception.InvalidPasswordException;
import io.github.dev_alan87.sales.security.jwt.JwtService;
import io.github.dev_alan87.sales.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public MyUser save(@RequestBody @Valid MyUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.save(user);
    }
    
    @PostMapping("/auth")
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) {
        try {
            MyUser user = MyUser.builder()
                    .username(credentials.getUsername())
                    .password(credentials.getPassword())
                    .build();
            
            UserDetails authenticatedUser = 
                    userService.authenticate(user);
            
            String token = jwtService.generateToken(user);
            
            return new TokenDTO(authenticatedUser.getUsername(), token);
            
        } catch (UsernameNotFoundException | InvalidPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}