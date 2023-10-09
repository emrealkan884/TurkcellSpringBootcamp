package com.turkcell.spring.controllers;

import com.turkcell.spring.business.abstracts.ProductService;
import com.turkcell.spring.entities.concretes.Product;
import com.turkcell.spring.entities.dtos.product.ProductForAddDto;
import com.turkcell.spring.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.entities.dtos.product.ProductForUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductsController {
    private final ProductService productService;
    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid ProductForAddDto productForAddDto){
        productService.add(productForAddDto);
        return new ResponseEntity("Ürün eklendi",HttpStatus.CREATED);
    }

    @GetMapping()
    public List<ProductForListingDto> getAll() {
        return productService.getAll();
    }


    @GetMapping("getById/{id}")
    public ProductForGetByIdDto getByIdWithDto(@PathVariable short id){
        return productService.getById(id);
    }

    @GetMapping("getByName")
    public List<Product> getProductsByName(@RequestParam("name") String name) {
        List<Product> products = productService.findByProductNameContaining(name);
        return products;
    }

    @GetMapping("getByUnitPriceGreaterThan")
    public List<Product> findByUnitPriceGreaterThan(@RequestParam("unitPrice") float unitPrice) {
        List<Product> products = productService.findByUnitPriceGreaterThan(unitPrice);
        return products;
    }

    @GetMapping("getProductsByDiscontinuedTrueNativeSQL")
    public List<Product> findProductsByCategoryNativeSQL() {
        List<Product> products = productService.findByProductsByDiscontinuedTrueNativeSQL();
        return products;
    }

    @GetMapping("getByUnitsInStockLessThan")
    public List<Product> findByUnitsInStockLessThan(@RequestParam("unitsInStock") short unitsInStock) {
        List<Product> products = productService.findByUnitsInStockLessThan(unitsInStock);
        return products;
    }

    @GetMapping("getProductsByUnitsOnOrderNativeSql")
    public List<Product> findProductsByUnitsOnOrderNativeSql(@RequestParam("unitsOnOrder") short unitsOnOrder) {
        List<Product> products = productService.findProductsByUnitsOnOrderNativeSql(unitsOnOrder);
        return products;
    }

    @GetMapping("getProductsOutOfStock")
    public List<Product> findProductsOutOfStock() {
        List<Product> products = productService.findProductsOutOfStock();
        return products;
    }

    @GetMapping("getByProductOrderByProductNameDesc")
    public List<Product> findByProductOrderByProductNameDesc() {
        List<Product> products = productService.findByProductOrderByProductNameDesc();
        return products;
    }

    @GetMapping("getProductsByUnitPriceRange")
    public List<Product> findProductsByUnitPriceRange(@RequestParam("deger1") float deger1,@RequestParam("deger2") float deger2) {
        List<Product> products = productService.findProductsByUnitPriceRange(deger1,deger2);
        return products;
    }

    @PutMapping("update/{id}")
    public ResponseEntity update(@PathVariable short id,@RequestBody @Valid ProductForUpdateDto productForUpdateDto){
        productService.update(id,productForUpdateDto);
        return new ResponseEntity("Ürün güncellendi", HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable short id) {
        productService.delete(id);
        return new ResponseEntity("Ürün silindi", HttpStatus.CREATED);
    }

}
