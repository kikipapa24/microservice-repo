package com.order.orderservice.request;

import java.math.BigDecimal;

public record OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price,Integer quantity) {
}
