package io.matoshri.customer.controller;


import io.matoshri.customer.dto.ApiRequest;
import io.matoshri.customer.dto.ApiResponse;
import io.matoshri.customer.entity.CustomerOrders;
import io.matoshri.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody ApiRequest request) {
        customerService.createCustomer(request);
    }

    @GetMapping("/{customer-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse findCustomerById(@PathVariable("customer-id") Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApiResponse> findAll() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{customer-id}/orders")
    public List<CustomerOrders> findAllOrders(@PathVariable("customer-id") Long customerId) {
        return customerService.findAllOrders(customerId);
    }
}
