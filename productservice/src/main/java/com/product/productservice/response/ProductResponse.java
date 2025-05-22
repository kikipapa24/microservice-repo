package com.product.productservice.response;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String desc, BigDecimal price) {
}
