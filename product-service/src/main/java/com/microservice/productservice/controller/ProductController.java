package com.microservice.productservice.controller;

import com.microservice.productservice.dto.request.ProductRequest;
import com.microservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return  new ResponseEntity<>("ok",HttpStatus.CREATED);
    }

    @GetMapping("")
    public  ResponseEntity<?> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }
}
