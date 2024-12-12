package com.example.ecommerce_prj4.controller;

import com.example.ecommerce_prj4.respone.ApiRespone;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiRespone HomeControllerHandler(){
        ApiRespone apiRespone = new ApiRespone();
        apiRespone.setMessage("Hello World");
        return apiRespone;
    }
}
