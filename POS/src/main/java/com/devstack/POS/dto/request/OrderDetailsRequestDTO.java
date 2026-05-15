package com.devstack.POS.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsRequestDTO {

    @NotNull(message = "Product ID is Required")
    private UUID productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 10000, message = "Quantity must not exceed 10000")
    private int qty;

    @Positive(message = "Unit Price must be a positive value")
    @DecimalMin(value = "0.01", message = "Unit Price must be greater than 0.01")
    @DecimalMax(value = "9999999.00", message = "Unit Price must be less than 9,999,999.00")
    private double unitPrice;

}
