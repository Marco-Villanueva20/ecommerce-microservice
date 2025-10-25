package com.ecommerce.customer_service.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
        .id(customerRequest.id())
        .firstName(customerRequest.firstName())
        .lastName(customerRequest.lastName())
        .email(customerRequest.email())
        .phone(customerRequest.phone())
        .address(customerRequest.address())
        .city(customerRequest.city())
        .build();
       
    }

    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .phone(customer.getPhone())
        .address(customer.getAddress())
        .city(customer.getCity())
        .build();
    }

}
