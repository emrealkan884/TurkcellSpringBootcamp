package com.turkcell.spring.entities.dtos.category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryForUpdateDto {

    @NotNull()
    @Min(1)
    private int id;

    @NotNull
    @NotBlank(message = "Kategori ismi boş olamaz")
    @Size(min=3,max=20)
    private String categoryName;

    @NotBlank(message = "Açıklama boş olamaz")
    @Size(min=3,max=50)
    private String description;
}
