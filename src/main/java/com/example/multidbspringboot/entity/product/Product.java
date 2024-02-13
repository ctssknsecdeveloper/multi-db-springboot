package com.example.multidbspringboot.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Product {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private String name;
}
