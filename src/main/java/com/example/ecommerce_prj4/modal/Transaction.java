package com.example.ecommerce_prj4.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;
    @OneToOne
    private Order order;
    @ManyToOne
    private Seller seller;
    private LocalDate date = LocalDate.now();
}
