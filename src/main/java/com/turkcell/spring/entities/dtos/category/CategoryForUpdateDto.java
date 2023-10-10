package com.turkcell.spring.entities.dtos.category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryForUpdateDto {

    @NotNull(message = "{categoryDoesNotExistWithGivenId}")
    @Min(value = 1,message = "{categoryIdShouldBeMinimum}")
    private int id;

    @NotBlank(message = "{categoryNameCantBeEmpty}")
    @Size(min=3,max=20)
    private String categoryName;

    @NotBlank(message = "{descriptionCantBeEmpty}")
    @Size(min=3,max=50, message = "{descriptonShouldBeMinimumAndMaximum}")
    private String description;
}
