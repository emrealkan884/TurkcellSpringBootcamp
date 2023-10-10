package com.turkcell.spring.entities.dtos.product;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductForAddDto {
    @NotBlank(message = "{productNameCantBeEmpty}")
    @Size(min = 3, max = 50,message = "{productNameShouldBeMinimumAndMaximum}")
    private String productName;

    @NotNull(message = "{unitPriceCantBeEmpty}")
    @Min(value = 0,message = "{unitPriceShouldBeMinimum}")
    private float unitPrice;

    private String quantityPerUnit;

    @NotNull(message = "{unitsInStockCantBeEmpty}")
    @Positive(message = "{unitsInStockShouldBeGreaterThan0}")
    private short unitsInStock;

    private short unitsOnOrder;

    @Min(value = 0,message = "{discontinuedShouldBeMinimum}")
    @Max(value = 1,message = "{discontinuedShouldBeMaximum}")
    private int discontinued;
    private short reorderLevel;

    @NotNull(message = "{supplierIdCantBeEmpty}")
    @Min(value = 1, message = "{supplerIdShouldBeGreaterThan0}")
    private short supplierId;

    @NotNull(message = "{categoryIdCantBeEmpty}")
    @Min(value = 1, message = "{categoryIdCantBeLessThanMinValue}")
    private int categoryId;
}
