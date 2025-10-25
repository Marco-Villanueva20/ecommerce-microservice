package com.ecommerce.customer_service.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
      
        return ResponseEntity.ok(customerService.saveCustomer(customerRequest));
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        customerService.saveCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }
    
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String customerId){
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }

}
