package com.example.spring_init.controller;

import com.example.spring_init.model.mongo.UserModel;
import com.example.spring_init.model.request.CreateUserModel;
import com.example.spring_init.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/createDefaultUser", method = RequestMethod.POST)
    public ResponseEntity createDefaultUser(@RequestBody CreateUserModel user){
        try {
            if (userRepository.findUserByName(user.getName()).isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user.toUserModel());
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity registerUser(@RequestBody CreateUserModel user){
        try {
            if (userRepository.findUserByName(user.getName()).isPresent())
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken. Please try again");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user.toUserModel());
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
