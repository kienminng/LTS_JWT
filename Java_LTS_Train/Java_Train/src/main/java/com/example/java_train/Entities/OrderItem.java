package com.example.java_train.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private int Quantity;
    private String DVT;
    private double Price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order ;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
