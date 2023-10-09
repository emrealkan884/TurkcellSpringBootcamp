package com.turkcell.spring.business.concretes;

import com.turkcell.spring.business.abstracts.ProductService;
import com.turkcell.spring.business.exceptions.BusinessException;
import com.turkcell.spring.entities.concretes.Category;
import com.turkcell.spring.entities.concretes.Product;
import com.turkcell.spring.entities.concretes.Supplier;
import com.turkcell.spring.entities.dtos.product.*;
import com.turkcell.spring.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void add(ProductForAddDto productForAddDto) {

        productWithSameNameShouldNotExist(productForAddDto.getProductName());

        Product product = new Product();
        product.setProductName(productForAddDto.getProductName());
        product.setUnitPrice(productForAddDto.getUnitPrice());
        product.setQuantityPerUnit(productForAddDto.getQuantityPerUnit());
        product.setUnitsInStock(productForAddDto.getUnitsInStock());
        product.setUnitsOnOrder(productForAddDto.getUnitsOnOrder());
        product.setReorderLevel(productForAddDto.getReorderLevel());
        product.setDiscontinued(0);

        Supplier s = new Supplier();
        s.setSupplierId(productForAddDto.getSupplierId());
        product.setSupplier(s);

        Category c = new Category();
        c.setCategoryId(productForAddDto.getCategoryId());
        product.setCategory(c);
        productRepository.save(product);
    }

    @Override
    public List<ProductForListingDto> getAll() {
        return productRepository.getAllForListing();
    }

    @Override
    public ProductForGetByIdDto getById(short id) {
        return productRepository.getForById(id);
    }

    @Override
    public void update(short id, ProductForUpdateDto productForUpdateDto) {
        productWithSameNameShouldNotExist(productForUpdateDto.getProductName());

        Product product = productRepository.getReferenceById(id);
        product.setProductName(productForUpdateDto.getProductName());
        product.setUnitPrice(productForUpdateDto.getUnitPrice());
        product.setQuantityPerUnit(productForUpdateDto.getQuantityPerUnit());
        product.setUnitsInStock(productForUpdateDto.getUnitsInStock());
        product.setUnitsOnOrder(productForUpdateDto.getUnitsOnOrder());
        product.setReorderLevel(productForUpdateDto.getReorderLevel());

        Supplier s = new Supplier();
        s.setSupplierId(productForUpdateDto.getSupplierId());
        product.setSupplier(s);

        Category c = new Category();
        c.setCategoryId(productForUpdateDto.getCategoryId());
        product.setCategory(c);

        productRepository.save(product);
    }

    @Override
    public void delete(short id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public List<Product> findByProductNameContaining(String name) {
        return productRepository.findByProductNameContaining(name);
    }

    @Override
    public List<Product> findByUnitPriceGreaterThan(float unitPrice) {
        return productRepository.findByUnitPriceGreaterThan(unitPrice);
    }

    @Override
    public List<Product> findByUnitsInStockLessThan(short unitsInStock) {
        return productRepository.findByUnitsInStockLessThan(unitsInStock);
    }

    @Override
    public List<Product> findByProductsByDiscontinuedTrueNativeSQL() {
        return productRepository.findProductsByDiscontinuedTrueNativeSQL();
    }

    @Override
    public List<Product> findProductsByUnitsOnOrderNativeSql(short unitsOnOrder) {
        return productRepository.findProductsByUnitsOnOrderNativeSql(unitsOnOrder);
    }

    @Override
    public List<Product> findProductsByUnitPriceRange(float deger1, float deger2) {
        return productRepository.findProductsByUnitPriceRange(deger1, deger2);
    }

    @Override
    public List<Product> findProductsOutOfStock() {
        return productRepository.findProductsOutOfStock();
    }

    @Override
    public List<Product> findByProductOrderByProductNameDesc() {
        return productRepository.findByProductOrderByProductNameDesc();
    }

    private void productWithSameNameShouldNotExist(String productName) {
        Product productWithSameName = productRepository.findByProductName(productName);
        if (productWithSameName != null) {
            throw new BusinessException("Aynı ürün isminden 2 ürün bulunamaz");
        }
    }

    public short checkUnitsInStockAndQuantityAndReturnQuantity(short productId,short quantity) {
        ProductForGetByIdDto product = getById(productId);
        short beforeUpdateUnitsInStock = product.getUnitsInStock();
        if (quantity > product.getUnitsInStock()) {
            productRepository.updateUnitsInStock(product.getProductId(),(short)0);
            return beforeUpdateUnitsInStock;
        }
        return (short)(product.getUnitsInStock()-quantity);
    }
}