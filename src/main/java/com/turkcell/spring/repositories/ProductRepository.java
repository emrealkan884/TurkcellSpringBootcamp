package com.turkcell.spring.repositories;

import com.turkcell.spring.business.abstracts.ProductService;
import com.turkcell.spring.entities.concretes.Product;
import com.turkcell.spring.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.entities.dtos.product.ProductForListingDto;
import com.turkcell.spring.entities.dtos.product.ProductForOrderAddDto;
import com.turkcell.spring.entities.dtos.product.ProductForUpdateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Short> {

    //Derived
    //findByProductNameContaining,girilen değer product tablosu içinde varsa getir.
    List<Product> findByProductNameContaining(String productName);

    //findByUnitPriceGreaterThan,verilen fiyatın üzerindekileri getir
    List<Product> findByUnitPriceGreaterThan(float unitPrice);

    //findByUnitsInStockLessThan, belirli bir stok miktarının altında bulunan ürünleri getirmek için
    List<Product> findByUnitsInStockLessThan(short unitsInStock);
    Product findByProductName(String productName);

    @Query(value = "Select new com.turkcell.spring.entities.dtos.product.ProductForListingDto" +
            "(p.productId,p.productName,p.unitPrice,p.quantityPerUnit,p.unitsInStock,p.unitsOnOrder,p.discontinued,od.quantity,od.discount) " +
            "from Product p Inner join p.orderDetails od")
    List<ProductForListingDto> getAllForListing();


    //@Modifying anotasyonu, bu metodun bir güncelleme işlemi gerçekleştirdiğini belirtir ve bu nedenle sonuç döndürmesi gerekmeyeceğini gösterir.
    @Modifying
    @Query(value = "UPDATE products SET units_in_stock = ?2 WHERE product_id = ?1",nativeQuery = true)
    void updateUnitsInStock(short productId, short newStock);

    @Query(value = "Select new com.turkcell.spring.entities.dtos.product.ProductForOrderAddDto" +
            "(p.unitPrice,p.unitsInStock) " +
            "from Product p where p.productId =:productId")
    ProductForOrderAddDto findUnitPriceAndUnitsInStockById(short productId);

    @Query(value = "Select new com.turkcell.spring.entities.dtos.product.ProductForGetByIdDto" +
            "(p.productId,p.unitPrice,p.productName,p.quantityPerUnit,p.unitsInStock,p.unitsOnOrder,p.reorderLevel) " +
            "from Product p Where p.productId = :productId")
    ProductForGetByIdDto getForById(short productId);


    @Query(value = "SELECT p FROM Product p Order By p.productName Desc", nativeQuery = false)
    List<Product> findByProductOrderByProductNameDesc();

    //findProductsOutOfStock,stokta bulunmayan ürünleri getirmek için
    @Query(value = "SELECT p FROM Product p WHERE p.unitsInStock = 0", nativeQuery = false)
    List<Product> findProductsOutOfStock();

    //findProductsByPriceRange,belirli bir fiyat aralığına düşen ürünleri getirmek için
    @Query(value = "SELECT p FROM Product p WHERE p.unitPrice BETWEEN :deger1 and :deger2", nativeQuery = false)
    List<Product> findProductsByUnitPriceRange(float deger1,float deger2);

    @Query(value = "Select * from products Where discontinued = 1  ",nativeQuery = true)
    List<Product> findProductsByDiscontinuedTrueNativeSQL();

    //findProductsByUnitsOnOrderNativeSql,girilen sayı kadar siparişte olan ürünleri getir.
    @Query(value = "Select * from products Where units_on_order = :unitsOnOrder",nativeQuery = true)
    List<Product> findProductsByUnitsOnOrderNativeSql(short unitsOnOrder);

    @Query(value = "Select * from products Where unit_price > 100",nativeQuery = true)
    List<Product> findProductsByUnitPriceGreaterThan100();
}
