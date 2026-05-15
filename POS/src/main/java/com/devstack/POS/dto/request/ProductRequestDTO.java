package com.devstack.POS.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Description is Required")
    @Size(min = 2, max = 255, message = "Description must be between 5 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s., \\-]+$", message = "Description must contain only letters, numbers and spaces")
    private String description;

    @Positive(message = "Unit Price must be a positive value")
    @DecimalMin(value = "0.01", message = "Unit Price must be greater than 0.01")
    @DecimalMax(value = "9999999.00", message = "Unit Price must be less than 9,999,999.00")
    private double unitPrice;

    @Min(value = 0, message = "Quantity on Hand cannot be negative")
    @Max(value = 100000, message = "Quantity on Hand must be less than 100,000")
    private int qtyOnHand;
}
