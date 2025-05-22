package com.inventory.inventoryservice.service;

import com.inventory.inventoryservice.dao.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepo;

    @Override
    public Boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepo.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
    }
}
