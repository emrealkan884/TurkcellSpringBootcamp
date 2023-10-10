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
    @NotBlank(message = "{productNameCantBeEmpty}")
    @Size(min = 1, max = 50, message = "{productNameShouldBeMinimumAndMaximum}")
    private String productName;

    @Min(value = 0,message = "{unitPriceShouldBeMinimum}")
    private float unitPrice;

    private String quantityPerUnit;

    @NotNull(message = "{unitsInStockCantBeEmpty}")
    @Positive(message = "{unitsInStockShouldBeGreaterThan0}")
    private short unitsInStock;

    private short unitsOnOrder;
    private short reorderLevel;

    @Min(value = 1, message = "{supplerIdShouldBeGreaterThan0}")
    private short supplierId;
    @Min(value = 1, message = "{categoryIdCantBeLessThanMinValue}")
    private int categoryId;
}
