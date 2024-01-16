package com.example.orderservice.service;


import com.example.orderservice.dto.OrderLineItemDto;
import com.example.orderservice.dto.request.OrderRequest;
import com.example.orderservice.dto.respone.InventoryRespone;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderLineItem;
import com.example.orderservice.repository.OrderLineItemRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final WebClient.Builder webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        Order order_saved = orderRepository.save(order);
        List<OrderLineItemDto> orderLineItemDtoList = orderRequest.getOrderLineItemDtoList();
        //Lấy list SkuCode
        List<String> skuCodes = orderLineItemDtoList
                .stream()
                .map(OrderLineItemDto::getSkuCode)
                .toList();
        //Gọi API đến InventorySevice kiểm tra xem danh sách skucode trong kho
        InventoryRespone[] inventoryRespones =  webClient.build().get()
                .uri("http://inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryRespone[].class)
                .block();
        //Kiểm tra xem tất cả còn số lượng không
        boolean allMatch = Arrays.stream(inventoryRespones).allMatch(InventoryRespone::isInStock);
       if(allMatch){
           for(OrderLineItemDto orderLineItemDto:orderLineItemDtoList ){
               OrderLineItem orderLineItem = maptToDto(orderLineItemDto);
               orderLineItem.setOrder(order_saved);
               orderLineItemRepository.save(orderLineItem);
           }
       }else {
           throw new IllegalArgumentException("Product is not in stock");
       }

    }

    private OrderLineItem maptToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());

        return orderLineItem;
    }


}
