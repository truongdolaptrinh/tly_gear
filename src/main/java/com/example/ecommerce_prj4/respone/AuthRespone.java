package com.example.ecommerce_prj4.respone;

import com.example.ecommerce_prj4.domain.USER_ROLE;

public class AuthRespone {
    private String jwtToken;
    private String message;
    private USER_ROLE role;


    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }
}
