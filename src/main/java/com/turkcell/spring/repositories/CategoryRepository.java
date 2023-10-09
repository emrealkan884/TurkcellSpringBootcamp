package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.Category;
import com.turkcell.spring.entities.dtos.category.CategoryForGetByIdDto;
import com.turkcell.spring.entities.dtos.category.CategoryForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "Select new com.turkcell.spring.entities.dtos.category.CategoryForListingDto" +
            "(c.categoryName,c.description) from Category c")
    List<CategoryForListingDto> getForListing();

    @Query(value = "Select new com.turkcell.spring.entities.dtos.category.CategoryForGetByIdDto" +
            "(c.categoryName,c.description) from Category c " +
            "Where c.categoryId = :categoryId")
    CategoryForGetByIdDto getForById(int categoryId);


    //İsimlendirme önemli.
    Category findByCategoryName(String categoryName);

    //JPQL=>JPA nın sql e benzer versiyonu
    //JPQL=> Tablo ismi yerine entity yaz!! Where den sonra category_name yazmak yerine categoryName(entitydeki) yaz
    @Query(value = "SELECT new com.turkcell.spring.entities.dtos.category.CategoryForListingDto" +
            "(c.categoryName,c.description) from Category c " +
            "WHERE c.categoryName LIKE %:name%", nativeQuery = false)
    List<CategoryForListingDto> findByCategoryNameContaining(@Param("name") String categoryName);


    //Direkt sql sorgusu
    @Query(value = "SELECT * FROM categories  WHERE category_name LIKE %:categoryName%", nativeQuery = true)
    List<Category> searchNative(String categoryName);
}
