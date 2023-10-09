package com.turkcell.spring.business.abstracts;

import com.turkcell.spring.entities.concretes.Category;
import com.turkcell.spring.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.entities.dtos.category.CategoryForGetByIdDto;
import com.turkcell.spring.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.entities.dtos.category.CategoryForUpdateDto;

import java.util.List;

public interface CategoryService {
    void add(CategoryForAddDto categoryForAddDto);
    void delete(int id);
    void update(CategoryForUpdateDto categoryForUpdateDto);
    List<CategoryForListingDto> getAll();
    CategoryForGetByIdDto getById(int id);
    List<Category> searchNative(String categoryName);
    Category findByCategoryName(String categoryName);
    List<CategoryForListingDto> findByCategoryNameContaining(String categoryName);
}
