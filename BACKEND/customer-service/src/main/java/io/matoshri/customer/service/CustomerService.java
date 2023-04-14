package io.matoshri.customer.service;

import io.matoshri.customer.dto.ApiRequest;
import io.matoshri.customer.dto.ApiResponse;
import io.matoshri.customer.entity.Customer;
import io.matoshri.customer.exception.ResourceNotFoundException;
import io.matoshri.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public void createCustomer(ApiRequest request) {
        Customer customer = Customer.builder()
                .customerName(request.getCustomerName())
                .address(request.getAddress())
                .contact(request.getContact())
                .email(request.getEmail())
                .build();
        customerRepository.save(customer);
        log.info("Created new Customer - {}", customer.getCustomerName());
    }

    public ApiResponse findCustomerById(final Long customerId) {
        Customer customer = findById(customerId);
        return ApiResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .contact(customer.getContact())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .build();
    }

    private Customer findById(final Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer doesn't exists: "+customerId));
    }
}
