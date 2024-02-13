package com.example.multidbspringboot.repository.product;


import com.example.multidbspringboot.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
