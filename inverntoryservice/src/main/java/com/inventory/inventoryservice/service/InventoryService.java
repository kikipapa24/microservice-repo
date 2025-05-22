package com.inventory.inventoryservice.service;

public interface InventoryService {
Boolean isInStock(String sku_code,Integer quantity);
}
