package com.microservice.productservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.productservice.dto.request.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers // Dùng để test trong container
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
    @Container // Khởi tạo vùng chứa test mongo
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("4.4.2");

    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper objectMapper;
    @DynamicPropertySource // Tạo kết nối spring boot đến container đó
    static  void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productReuqestString = objectMapper.writeValueAsString(productRequest);
        mockMVC.perform(MockMvcRequestBuilders.post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productReuqestString))
                .andExpect(status().isCreated());
        //Đoạn test này giả lập một MVC gửi request tới với cotent là productRequest và hi vọng status trả về là created
    }
    private ProductRequest getProductRequest(){
            return ProductRequest.builder()
                    .name("iphone 13")
                    .description("release 2023")
                    .price(BigDecimal.valueOf(1200))
                    .build();
    }

}
