package com.microservice.productservice.service.Impl;

import com.microservice.productservice.Mapper.ProductMapper;
import com.microservice.productservice.dto.request.ProductRequest;
import com.microservice.productservice.dto.respone.ProductRespone;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.ProductRepository;
import com.microservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository; // chổ này nên hiểu tại sao lại k dùng autowire
    private final ProductMapper productMapper;
    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product have id  " + product.getId() + "is saved");
    }

    @Override
    public List<ProductRespone> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponeList(products);
    }
}
