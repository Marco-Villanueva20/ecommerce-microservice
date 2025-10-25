package com.ecommerce.customer_service.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.customer_service.exception.CustomerNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String saveCustomer(CustomerRequest customerRequest) {
        var customer = customerRequest.id() == null ? customerMapper.toCustomer(customerRequest)
                : updateExistingCustomer(customerRequest);
        return customerRepository.save(customer).getId();

    }

    public CustomerResponse getCustomerById(String customerId) {
        var customer = customerRepository
                .findById(customerId)
                .map(customerMapper::toCustomerResponse).orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerId)));
        return customer;
    }

    public List<CustomerResponse> getCustomers() {
        return customerRepository
                .findAll()
                .stream().map(customer -> customerMapper.toCustomerResponse(customer))
                .toList();
    }

    public void deleteCustomerById(String customerId) {
        customerRepository
                .findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerId)));
        customerRepository.deleteById(customerId);
    }

    private Customer updateExistingCustomer(CustomerRequest customerRequest) {
        var existingCustomer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerRequest.id())));

        existingCustomer.setFirstName(customerRequest.firstName());
        existingCustomer.setLastName(customerRequest.lastName());
        existingCustomer.setEmail(customerRequest.email());
        existingCustomer.setPhone(customerRequest.phone());
        existingCustomer.setAddress(customerRequest.address());
        existingCustomer.setCity(customerRequest.city());
        return existingCustomer;
    }

}
