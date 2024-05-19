package com.devkings.productservice.service;

import com.devkings.productservice.ProductServiceApplication;
import com.devkings.productservice.dto.ProductRequest;
import com.devkings.productservice.dto.ProductResponse;
import com.devkings.productservice.model.Product;
import com.devkings.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} saved successfully",product.getId());
    }

    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::maptoProductResponse).toList();

    }

    private ProductResponse maptoProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public ProductResponse getProduct(String id) {
        Optional<Product> productOptional =productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return maptoProductResponse(product);
        }
        return null;
    }

}
