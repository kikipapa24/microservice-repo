package com.product.productservice.request;

import java.math.BigDecimal;

public record ProductRequest(String id, String name, String desc, BigDecimal price) {
}
