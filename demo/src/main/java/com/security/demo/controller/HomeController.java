package com.security.demo.controller;

import com.security.demo.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;


    @PostMapping("/register")
    public String registerUser(@RequestBody MyUser myUser) {

        System.out.println(myUser.getRole());

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(myUser.getRole()));

        String encodedPassword = passwordEncoder.encode(myUser.getPassword());

        User user = new User(myUser.getUserName(), encodedPassword, authorities);

        jdbcUserDetailsManager.createUser(user);

        return "User created :)";
    }

    @GetMapping("/")
    public String home() {
        return "Welcome Home";
    }
}
