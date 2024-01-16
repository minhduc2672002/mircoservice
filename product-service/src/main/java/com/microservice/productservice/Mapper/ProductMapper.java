package com.microservice.productservice.Mapper;

import com.microservice.productservice.dto.respone.ProductRespone;
import com.microservice.productservice.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductRespone toRespone(Product product);

    List<ProductRespone> toResponeList(List<Product> productList);

}
