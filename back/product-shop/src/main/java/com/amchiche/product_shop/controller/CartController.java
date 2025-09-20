package com.amchiche.product_shop.controller;

import com.amchiche.product_shop.model.CartItem;
import com.amchiche.product_shop.model.Product;
import com.amchiche.product_shop.model.User;
import com.amchiche.product_shop.repository.CartItemRepository;
import com.amchiche.product_shop.repository.ProductRepository;
import com.amchiche.product_shop.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartController(UserRepository userRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @GetMapping("/{id}")
    public List<CartItem> getCart(@PathVariable Long id){
        User u =  userRepository.findById(id).orElse(null);
        return cartItemRepository.findByUser(u);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Integer> body){
        Long productId = Long.valueOf(body.get("productId"));
        Integer qty = body.getOrDefault("quantity", 1);
        Product product = productRepository.findById(productId).orElseThrow();
        //TODO the current user with spring security
        return ResponseEntity.ok(ResponseEntity.notFound());
    }

}
