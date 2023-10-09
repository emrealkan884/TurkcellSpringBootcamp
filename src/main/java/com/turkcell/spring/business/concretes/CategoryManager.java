package com.turkcell.spring.business.concretes;

import com.turkcell.spring.business.abstracts.CategoryService;
import com.turkcell.spring.business.exceptions.BusinessException;
import com.turkcell.spring.entities.concretes.Category;
import com.turkcell.spring.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.entities.dtos.category.CategoryForGetByIdDto;
import com.turkcell.spring.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.entities.dtos.category.CategoryForUpdateDto;
import com.turkcell.spring.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void add(CategoryForAddDto categoryForAddDto) {
        Category category = Category.builder()
                .categoryName(categoryForAddDto.getCategoryName())
                .description(categoryForAddDto.getDescription())
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        Category categoryToDelete = returnCategoryByIdIfExist(id);
        categoryRepository.delete(categoryToDelete);
    }

    @Override
    public void update(CategoryForUpdateDto categoryForUpdateDto) {
        Category categoryToUpdate = returnCategoryByIdIfExist(categoryForUpdateDto.getId());
        categoryToUpdate.setCategoryName(categoryForUpdateDto.getCategoryName());
        categoryToUpdate.setDescription(categoryToUpdate.getDescription());
        categoryRepository.save(categoryToUpdate);
    }

    @Override
    public List<CategoryForListingDto> getAll() {
        return categoryRepository.getForListing();
    }

    @Override
    public CategoryForGetByIdDto getById(int id) {
        return categoryRepository.getForById(id);
    }

    @Override
    public List<Category> searchNative(String categoryName) {
        return categoryRepository.searchNative(categoryName);
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<CategoryForListingDto> findByCategoryNameContaining(String categoryName) {
        return categoryRepository.findByCategoryNameContaining(categoryName);
    }

    public  void categoryWithCategoryNameShouldBeginUpperCase(String categoryName){
        if(!Character.isUpperCase(categoryName.charAt(0))){
            throw new BusinessException("Kategori ismi büyük hafle başlamalıdır.");
        }
    }

    public void categoryWithDescriptionLengthGreaterThanCategoryNameLength(String description, String categoryName){
        if(categoryName.length() > description.length()){
            throw new BusinessException("Kategori ismi açıklamadan uzun olamaz.");
        }
    }
    public void categoryShouldNotBeMoreThan10(){
        List<CategoryForListingDto> category = categoryRepository.getForListing();
        if (category.size() >=10){
            throw new BusinessException("10'dan fazla kategori bulunamaz.");}
    }

    public void categoryWithSameNameShouldNotExist(String categoryName){
        //Aynı isimde iki kategori olmamalı
        Category categoryWithSameName = categoryRepository.findByCategoryName(categoryName);
        if (categoryWithSameName != null){
            //Business kuralı hatası
            throw new BusinessException("Aynı kategori isminden iki kategori bulunamaz");
        }
    }

    private Category returnCategoryByIdIfExist(int id){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category==null)
            throw new BusinessException("Böyle bir kategori bulunamadı.");
        return category;
    }
}
