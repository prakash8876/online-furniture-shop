package io.matoshri.customer.service;

import io.matoshri.customer.dto.ApiRequest;
import io.matoshri.customer.dto.ApiResponse;
import io.matoshri.customer.entity.Customer;
import io.matoshri.customer.entity.CustomerOrders;
import io.matoshri.customer.exception.ResourceNotFoundException;
import io.matoshri.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

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

    public List<ApiResponse> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ApiResponse mapToResponse(Customer customer) {
        return ApiResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .contact(customer.getContact())
                .build();
    }

    public List<CustomerOrders> findAllOrders(Long customerId) {
        Customer customer = findById(customerId);
        // call order-service with customerId
        // and get all the orders present in list
        CustomerOrders customerOrders = restTemplate.getForObject("url", CustomerOrders.class);
        return List.of(customerOrders);
    }
}
