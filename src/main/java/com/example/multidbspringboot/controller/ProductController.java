package com.example.multidbspringboot.controller;


import com.example.multidbspringboot.entity.product.Product;
import com.example.multidbspringboot.repository.product.ProductRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class ProductController {

    //@Autowired
    private ProductRepo productRepo;

    @PostMapping
    public Product saveOrder(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @GetMapping
    public List<Product> getAllOrder(){
        return productRepo.findAll();
    }
}
