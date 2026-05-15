package com.devstack.POS.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRequestDTO {

    @NotNull(message = "Order Date is Required")
    @PastOrPresent(message = "Order Date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "Customer ID is Required")
    private UUID customerId;

    @NotNull(message = "Order Details are Required")
    @NotEmpty(message = "Order Details cannot be empty")
    @Size(max = 100, message = "Order Details cannot exceed 100 items")
    @Valid // ----------for each rows
    private List<OrderDetailsRequestDTO> details;

}
