package com.turkcell.spring.controllers;

import com.turkcell.spring.business.abstracts.CategoryService;
import com.turkcell.spring.entities.concretes.Category;
import com.turkcell.spring.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.entities.dtos.category.CategoryForGetByIdDto;
import com.turkcell.spring.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.entities.dtos.category.CategoryForUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    // DI
    // Spring IoC => Bağımlılıkların çözümlenmesi..
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryForListingDto> getCategories()
    {
        return categoryService.getAll();
    }

    @GetMapping("getByCategoryName")
    public List<CategoryForListingDto> getCategoriesByName(@RequestParam("name") String name){
        return categoryService.findByCategoryNameContaining(name);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
        categoryService.delete(id);
        return new ResponseEntity("Kategori silindi",HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity update(@RequestBody @Valid CategoryForUpdateDto request){
        categoryService.update(request);
        return new ResponseEntity("Kategori güncellendi",HttpStatus.CREATED);
    }


    @GetMapping("search")
    public List<Category> search(@RequestParam("name") String name){
        List<Category> categories = categoryService.searchNative(name);
        return categories;
    }

    @GetMapping("getById")
    public CategoryForGetByIdDto getCategoryById(@RequestParam("id") int id){
        return categoryService.getById(id);
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid CategoryForAddDto request){
        // Manual Mapleme
        // Auto Mapper => ModelMapper
        categoryService.add(request);
        //categoryRepository.save(category);
        return new ResponseEntity("Kategori eklendi", HttpStatus.CREATED);
    }
}
