package com.order.orderservice.service;

import com.order.orderservice.client.InventoryClient;
import com.order.orderservice.dao.OrderRepository;
import com.order.orderservice.model.Order;
import com.order.orderservice.request.OrderRequest;
import com.order.orderservice.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
      var isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        log.debug("place order invoked");
        Order order = new Order();
        if(isProductInStock) {
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
            log.info("Order created successfully");
        }else {
            throw new RuntimeException("Product with "+orderRequest.skuCode()+" is not in stock");
        }
        return new OrderResponse(order.getId(),order.getOrderNumber(),order.getSkuCode(),order.getPrice(),order.getQuantity());
    }

    @Override
    public OrderResponse getAllOrder() {
        return null;
    }


}
