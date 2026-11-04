package com.ejstore.productcatalog.service;

import com.ejstore.productcatalog.repository.ProductCategoryRepository;
import com.ejstore.productcatalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    // TODO: Implement approved use cases without adding unapproved business rules.
}
