package io.matoshri.item.service;

import io.matoshri.item.entity.Customer;
import io.matoshri.item.exception.ResourceNotFoundException;
import io.matoshri.item.repository.CustomerRepository;
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
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
        log.info("Created new Customer - {}", customer.getCustomerName());
    }

    public Customer findCustomerById(final Long customerId) {
        return findById(customerId);
    }

    private Customer findById(final Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer doesn't exists: "+customerId));
    }
}
