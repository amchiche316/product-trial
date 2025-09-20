package com.amchiche.product_shop.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor  @AllArgsConstructor @Builder
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @Column(length = 2000)
    private String description;
    private String image;
    private String category;
    private Double price;
    private Integer quantity;
    private String internatlReference;
    private Long shellId;
@Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    private Integer rating;
    private Long createdAt;
    private Long updatedAt;
@PrePersist
    public void prePersist(){
        long now = System.currentTimeMillis();
        this.createdAt =now;
        this.updatedAt =now;
    }
    @PreUpdate
    public void preUpdate(){
    this.updatedAt = System.currentTimeMillis();
    }

}
