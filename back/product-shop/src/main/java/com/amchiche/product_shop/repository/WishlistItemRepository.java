package com.amchiche.product_shop.repository;

import com.amchiche.product_shop.model.Product;
import com.amchiche.product_shop.model.User;
import com.amchiche.product_shop.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistItemRepository  extends JpaRepository<WishlistItem, Long> {

    List<WishlistItem> findByUser(User user);
    Optional<WishlistItem> findByUserAndProduct(User user, Product product);
}
