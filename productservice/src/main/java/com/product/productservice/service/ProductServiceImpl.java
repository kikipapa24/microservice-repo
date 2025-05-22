package com.product.productservice.service;

import com.product.productservice.dao.ProductRepository;
import com.product.productservice.model.Product;
import com.product.productservice.request.ProductRequest;
import com.product.productservice.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product .builder()
                .name(productRequest.name())
                .desc(productRequest.desc())
                .price(productRequest.price())
                .build();
        productRepo.save(product);
        log.info("product created successfully");
        return new ProductResponse(product.getId(), product.getName(), product.getDesc(), product.getPrice());
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDesc(), product.getPrice()))
                .toList();

    }
}
