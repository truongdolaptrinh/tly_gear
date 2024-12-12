package com.example.ecommerce_prj4.service;

import com.example.ecommerce_prj4.request.LoginRequest;
import com.example.ecommerce_prj4.respone.AuthRespone;
import com.example.ecommerce_prj4.respone.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthRespone signing(LoginRequest req);
}
