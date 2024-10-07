package com.example.spring_init.service;

import com.example.spring_init.model.mongo.UserModel;
import com.example.spring_init.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findUserByName(username).get();
        if(userModel == null)  {
            throw new UsernameNotFoundException("Name not found!");
        }
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(userModel.getRole()));

        return User.builder()
                .username(userModel.getName())
                .password(userModel.getPassword())
                .roles(userModel.getRole())
                .build();
    }

}
