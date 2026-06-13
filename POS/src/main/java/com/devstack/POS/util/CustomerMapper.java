package com.devstack.POS.util;

import com.devstack.POS.exception.ValidationException;
import com.devstack.POS.dto.request.CustomerRequestDTO;
import com.devstack.POS.dto.response.CustomerResponseDTO;
import com.devstack.POS.entity.Customer;
import org.springframework.stereotype.Component;

// import java.util.UUID;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequestDTO dto) {
        if (dto == null) throw new ValidationException("DTO Not Found");
        return Customer.builder()
               // .id(UUID.randomUUID())
                .name(dto.getName())
                .address(dto.getAddress())
                .salary(dto.getSalary())
                .build();
    }

    public CustomerResponseDTO toCustomerResponseDTO(Customer customer) {
        if (customer == null) throw new ValidationException("Customer Not Found");
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .salary(customer.getSalary())
                .address(customer.getAddress())
                .build();
    }


}
