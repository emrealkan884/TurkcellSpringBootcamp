package com.turkcell.spring.entities.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerForGetByIdDto {
    private String contactName;
    private String companyName;
    private String address;
    private String city;
    private String region;
    private String country;
}
