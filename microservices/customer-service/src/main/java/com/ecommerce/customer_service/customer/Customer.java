package com.ecommerce.customer_service.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Customer {
    @Id
    String id;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;
    String city;

}
