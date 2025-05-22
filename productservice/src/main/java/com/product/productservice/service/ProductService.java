package com.product.productservice.service;

import com.product.productservice.request.ProductRequest;
import com.product.productservice.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> findAllProducts();

}
