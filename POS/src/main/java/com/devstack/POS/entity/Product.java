package com.devstack.POS.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private UUID id;

    private String description;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "qty_on_hand")
    private Integer qtyOnHand;
}
