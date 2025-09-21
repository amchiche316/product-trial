package com.amchiche.product_shop.controller;

import com.amchiche.product_shop.model.Product;
import com.amchiche.product_shop.repository.ProductRepository;
import com.amchiche.product_shop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Product> all(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id){
        return productRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

//check if the user is an admin

    private boolean isAdmin(){

        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        String email = auth.getName();
        return "admin@admin.com".equalsIgnoreCase(email);
    }


    @PostMapping
    public ResponseEntity<?> create( @RequestBody Product p){
        if(!isAdmin()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin can create products");
        Product saved = productRepository.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product p){
        if(!isAdmin()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin can update products");
        return productRepository.findById(id).map(

                existing ->{
                    existing.setName(p.getName());
                    existing.setCode(p.getCode());
                    existing.setDescription(p.getDescription());
                    existing.setPrice(p.getPrice());
                    existing.setQuantity(p.getQuantity());
                    existing.setImage(p.getImage());
                    existing.setInventoryStatus(p.getInventoryStatus());
                    productRepository.save(existing);
                    return ResponseEntity.ok(existing);
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete (@PathVariable Long id){
        if(!isAdmin()) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admin can delete products");
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
