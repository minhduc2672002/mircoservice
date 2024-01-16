package com.tranminhduc.inventoryservice.controller;

import com.tranminhduc.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping()
    public ResponseEntity<?> isInstock(@RequestParam List<String> skuCode){
        return  new ResponseEntity<>(inventoryService.isInStock(skuCode), HttpStatus.OK);
    }

}
