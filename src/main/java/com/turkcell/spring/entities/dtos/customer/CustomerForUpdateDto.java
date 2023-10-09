package com.turkcell.spring.entities.dtos.customer;

import lombok.Builder;
import lombok.Data;

@Data
public class CustomerForUpdateDto {
    private String customerId;
    private String contactName;
    private String companyName;
    private String address;
    private String city;
    private String region;
    private String country;
}
