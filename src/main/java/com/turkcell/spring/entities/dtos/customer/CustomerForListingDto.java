package com.turkcell.spring.entities.dtos.customer;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerForListingDto {
    private String contactName;
    private String companyName;
    private String address;
    private String city;
    private String region;
    private String country;
}
