package com.microservice.productservice.service;

import com.microservice.productservice.dto.request.ProductRequest;
import com.microservice.productservice.dto.respone.ProductRespone;

import java.util.List;

public interface ProductService {
     void createProduct(ProductRequest productRequest);

     List<ProductRespone> getAllProduct();
}
