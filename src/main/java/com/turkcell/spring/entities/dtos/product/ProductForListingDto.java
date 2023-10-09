package com.turkcell.spring.entities.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForListingDto {

    private short productId;
    private String productName;
    private float unitPrice;
    private String quantityPerUnit;
    private short unitsInStock;
    private short unitsOnOrder;
    private int discontinued;

    private short quantity;
    private float discount;
}
