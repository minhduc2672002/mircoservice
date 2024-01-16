package com.tranminhduc.clouldgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClouldGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClouldGatewayApplication.class,args);
    }
}
