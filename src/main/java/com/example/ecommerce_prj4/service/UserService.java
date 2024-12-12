package com.example.ecommerce_prj4.service;

import com.example.ecommerce_prj4.modal.User;

public interface UserService {
    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
