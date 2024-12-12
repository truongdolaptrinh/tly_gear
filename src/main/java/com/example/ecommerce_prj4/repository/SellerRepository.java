package com.example.ecommerce_prj4.repository;

import com.example.ecommerce_prj4.modal.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);
}
