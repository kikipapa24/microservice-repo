package com.order.orderservice.service;

import com.order.orderservice.request.OrderRequest;
import com.order.orderservice.response.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
    OrderResponse getAllOrder();
}
