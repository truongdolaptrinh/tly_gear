package com.example.ecommerce_prj4.controller;

import com.example.ecommerce_prj4.domain.USER_ROLE;
import com.example.ecommerce_prj4.modal.User;
import com.example.ecommerce_prj4.respone.AuthRespone;
import com.example.ecommerce_prj4.respone.SignupRequest;
import com.example.ecommerce_prj4.service.AuthService;
import com.example.ecommerce_prj4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users/profile")
    public ResponseEntity<User> createUserHandler(@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(user);

    }
}
