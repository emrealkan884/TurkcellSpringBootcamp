package com.turkcell.spring.entities.dtos.supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierForGetByIdDto {
    private String companyName;
    private String contactName;
    private String address;
    private String city;
    private String country;
    private String phone;
}
