package com.example.ecommerce_prj4.service.impl;

import com.example.ecommerce_prj4.config.JwtProvider;
import com.example.ecommerce_prj4.modal.User;
import com.example.ecommerce_prj4.repository.UserRepository;
import com.example.ecommerce_prj4.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);

        return this.findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found with email - "+email);
        }
        return user;
    }
}
