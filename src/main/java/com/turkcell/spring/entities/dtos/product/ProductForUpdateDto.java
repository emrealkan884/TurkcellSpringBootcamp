package com.turkcell.spring.entities.dtos.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductForUpdateDto {
    @NotBlank(message = "Ürün ismi girmek zorunludur")
    @Size(min = 1, max = 50)
    private String productName;

    @Min(0)
    private float unitPrice;

    private String quantityPerUnit;

    @NotNull
    @Positive
    private short unitsInStock;

    private short unitsOnOrder;
    private short reorderLevel;

    @Min(1)
    private short supplierId;
    @Min(1)
    private int categoryId;
}
