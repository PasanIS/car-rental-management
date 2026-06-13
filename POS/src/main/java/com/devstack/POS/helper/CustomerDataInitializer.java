package com.devstack.POS.helper;

import com.devstack.POS.dto.request.CustomerRequestDTO;
import com.devstack.POS.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerDataInitializer implements CommandLineRunner {

    private final CustomerService customerService;

    @Override
    public void run(String @NonNull ... args) {
        log.info("Starting customer data initialization...");
        getSampleCustomers().forEach(dto -> {
            try {
                customerService.createCustomer(dto);
            } catch (Exception e) {
                log.warn("Failed to initialize customer '{}': {}", dto.getName(), e.getMessage());
            }
        });
        log.info("Customer data initialization completed.");
    }

    private List<CustomerRequestDTO> getSampleCustomers() {
        return List.of(
                new CustomerRequestDTO("Alice Johnson",    "123 Maple Street, New York",         75000.00),
                new CustomerRequestDTO("Bob Smith",        "456 Oak Avenue, Los Angeles",         82000.50),
                new CustomerRequestDTO("Carol Williams",   "789 Pine Road, Chicago",              63000.75),
                new CustomerRequestDTO("David Brown",      "321 Elm Street, Houston",             91000.00),
                new CustomerRequestDTO("Eva Martinez",     "654 Cedar Lane, Phoenix",             55000.25),
                new CustomerRequestDTO("Frank Taylor",     "987 Birch Boulevard, Philadelphia",   48000.00),
                new CustomerRequestDTO("Grace Anderson",   "147 Walnut Drive, San Antonio",       72000.00),
                new CustomerRequestDTO("Henry Thomas",     "258 Chestnut Court, San Diego",       68000.50),
                new CustomerRequestDTO("Isabella Jackson", "369 Willow Way, Dallas",              95000.00),
                new CustomerRequestDTO("James White",      "741 Aspen Circle, San Jose",          51000.75),
                new CustomerRequestDTO("Karen Harris",     "852 Spruce Street, Austin",           83000.00),
                new CustomerRequestDTO("Liam Martin",      "963 Poplar Place, Jacksonville",      47000.25),
                new CustomerRequestDTO("Mia Thompson",     "159 Magnolia Road, Fort Worth",       76000.00),
                new CustomerRequestDTO("Noah Garcia",      "357 Sycamore Lane, Columbus",         62000.50),
                new CustomerRequestDTO("Olivia Martinez",  "486 Redwood Avenue, Charlotte",       89000.00),
                new CustomerRequestDTO("Paul Robinson",    "624 Hickory Drive, Indianapolis",     53000.00),
                new CustomerRequestDTO("Quinn Clark",      "735 Juniper Street, San Francisco",   99000.75),
                new CustomerRequestDTO("Rachel Lewis",     "846 Cypress Court, Seattle",          71000.25),
                new CustomerRequestDTO("Samuel Lee",       "957 Dogwood Boulevard, Denver",       66000.00),
                new CustomerRequestDTO("Tina Walker",      "108 Bamboo Way, Nashville",           58000.50)
        );
    }
}
