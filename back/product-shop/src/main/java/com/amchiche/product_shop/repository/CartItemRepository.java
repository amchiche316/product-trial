package com.amchiche.product_shop.repository;

import com.amchiche.product_shop.model.CartItem;
import com.amchiche.product_shop.model.Product;
import com.amchiche.product_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository  <CartItem,  Long> {

    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
}
