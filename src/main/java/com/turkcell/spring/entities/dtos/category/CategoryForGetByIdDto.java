package com.turkcell.spring.entities.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryForGetByIdDto {
    private String categoryName;
    private String description;
}
