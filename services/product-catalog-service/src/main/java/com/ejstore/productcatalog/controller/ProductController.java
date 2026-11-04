package com.ejstore.productcatalog.controller;

import com.ejstore.productcatalog.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // TODO: Add HTTP endpoints after the API contract is approved.
}
