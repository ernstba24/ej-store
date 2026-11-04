package com.ejstore.productcatalog.repository;

import com.ejstore.productcatalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
