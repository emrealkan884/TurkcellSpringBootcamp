package com.turkcell.spring.entities.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryForAddDto {
    @NotBlank(message = "{categoryNameCantBeEmpty}")
    @Size(min=3,max=20,  message="{categoryNameShouldBeMinimum}")
    private String categoryName;

    @NotBlank(message = "{descriptionCantBeEmpty}")
    @Size(min=3,max=50, message = "{descriptonShouldBeMinimumAndMaximum}")
    private String description;
}
