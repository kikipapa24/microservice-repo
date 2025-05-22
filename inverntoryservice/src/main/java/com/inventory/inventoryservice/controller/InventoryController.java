package com.inventory.inventoryservice.controller;

import com.inventory.inventoryservice.service.InventoryService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Boolean isInStock(String skuCode,Integer quantity){
      return  inventoryService.isInStock(skuCode,quantity);
    }
}
