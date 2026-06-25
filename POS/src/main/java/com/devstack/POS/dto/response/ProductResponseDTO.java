package com.devstack.POS.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {

    private UUID id;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;
}
