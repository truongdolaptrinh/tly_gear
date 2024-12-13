package com.example.ecommerce_prj4.service;

import com.example.ecommerce_prj4.domain.USER_ROLE;
import com.example.ecommerce_prj4.request.LoginRequest;
import com.example.ecommerce_prj4.respone.AuthRespone;
import com.example.ecommerce_prj4.respone.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthRespone signing(LoginRequest req) throws Exception;
}
