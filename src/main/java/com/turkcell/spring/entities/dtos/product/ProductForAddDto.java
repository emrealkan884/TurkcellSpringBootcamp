package com.turkcell.spring.entities.dtos.product;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductForAddDto {
    @NotBlank(message = "Ürün ismi girmek zorunludur")
    @Size(min = 3, max = 50)
    private String productName;

    //@NotBlank(message = "Ürün fiyatı boş geçilemez")
    @NotNull(message = "Ürün fiyatı boş geçilemez")
    @Min(value = 0,message = "Ürün fiyatı 0'dan küçük olamaz")
    private float unitPrice;

    private String quantityPerUnit;

    @NotNull(message = "Stok miktarı boş geçilemez")
    @Positive(message = "Stok miktarı 0'dan küçük olamaz")
    private short unitsInStock;

    private short unitsOnOrder;

    @Min(0)
    @Max(1)
    private int discontinued;
    private short reorderLevel;

    @NotNull(message = "Supplier id boş geçilemez")
    @Min(value = 1, message = "Supplier id 0'dan büyük olmalıdır")
    private short supplierId;

    @NotNull(message = "Category id boş geçilemez")
    @Min(value = 1, message = "Category id 0'dan büyük olmalıdır")
    private int categoryId;
}
