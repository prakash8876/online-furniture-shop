package io.matoshri.customer.controller;


import io.matoshri.customer.dto.ApiRequest;
import io.matoshri.customer.dto.ApiResponse;
import io.matoshri.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }


}
