package com.turkcell.spring.entities.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForGetByIdDto {
    private short productId;
    private float unitPrice;
    private String productName;
    private String quantityPerUnit;
    private short unitsInStock;
    private short unitsOnOrder;
    private short reorderLevel;
}
