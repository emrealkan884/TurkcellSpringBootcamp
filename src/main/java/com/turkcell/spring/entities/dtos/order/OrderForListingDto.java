package com.turkcell.spring.entities.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForListingDto {
    private short orderId;
    //Customers
    private String contactName;

    private LocalDate orderDate;
    private float freight;

    //OrderDetails
    private short quantity;
}
