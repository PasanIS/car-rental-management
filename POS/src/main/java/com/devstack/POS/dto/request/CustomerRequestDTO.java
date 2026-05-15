package com.devstack.POS.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {

    @NotBlank(message = "Name is Required")
    @Size(min = 3, max = 100, message = "Name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    private String name;

    @NotBlank(message = "Address is Required")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;

    @Positive(message = "Salary must be a positive value")
    @DecimalMin(value = "0.01", message = "Salary must be greater than 0.01")
    @DecimalMax(value = "9999999.00", message = "Salary must be less than 9999999.00")
    private double salary;
}
