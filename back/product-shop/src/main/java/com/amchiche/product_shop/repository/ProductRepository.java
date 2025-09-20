package com.amchiche.product_shop.repository;

import com.amchiche.product_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
