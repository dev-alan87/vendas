package io.github.dev_alan87.sales.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.dev_alan87.sales.domain.entity.MyUser;
import io.github.dev_alan87.sales.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;
    private final PasswordEncoder encoder;
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public MyUser save(@RequestBody @Valid MyUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return service.save(user);
    }
    
}