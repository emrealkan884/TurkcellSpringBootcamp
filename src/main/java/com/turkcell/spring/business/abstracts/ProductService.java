package com.turkcell.spring.business.abstracts;

import com.turkcell.spring.entities.concretes.Product;
import com.turkcell.spring.entities.dtos.product.ProductForAddDto;
import com.turkcell.spring.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.entities.dtos.product.ProductForUpdateDto;

import java.util.List;

public interface ProductService {

    // Serviceler entityleri değil dtoları kullanmalı..
    void add(ProductForAddDto productForAddDto);
    List<ProductForListingDto> getAll();
    ProductForGetByIdDto getById(short id);
    void update(short id, ProductForUpdateDto productForUpdateDto);
    void delete(short id);
    short checkUnitsInStockAndQuantityAndReturnQuantity(short productId,short quantity);
    Product findByProductName(String productName);
    List<Product> findByProductNameContaining (String name);
    List<Product> findByUnitPriceGreaterThan(float unitPrice);
    List<Product> findByUnitsInStockLessThan(short unitsInStock);
    List<Product> findByProductsByDiscontinuedTrueNativeSQL();
    List<Product> findProductsByUnitsOnOrderNativeSql(short unitsOnOrder);
    List<Product> findProductsByUnitPriceRange(float deger1,float deger2);
    List<Product> findProductsOutOfStock();
    List<Product> findByProductOrderByProductNameDesc();
}
