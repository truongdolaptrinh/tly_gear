package com.example.ecommerce_prj4.repository;

import com.example.ecommerce_prj4.modal.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
