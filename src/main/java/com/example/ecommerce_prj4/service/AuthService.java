package com.example.ecommerce_prj4.service;

import com.example.ecommerce_prj4.respone.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest req);
}
