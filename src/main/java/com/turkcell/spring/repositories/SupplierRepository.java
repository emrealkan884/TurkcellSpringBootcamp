package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.Supplier;
import com.turkcell.spring.entities.dtos.supplier.SupplierForGetByIdDto;
import com.turkcell.spring.entities.dtos.supplier.SupplierForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Short> {
    @Query(value = "Select new com.turkcell.spring.entities.dtos.supplier.SupplierForListingDto" +
            "(s.companyName,s.contactName,s.address,s.city,s.country,s.phone) from Supplier s ")
    List<SupplierForListingDto> getForListing();

    @Query(value = "Select new com.turkcell.spring.entities.dtos.supplier.SupplierForGetByIdDto" +
            "(s.companyName,s.contactName,s.address,s.city,s.country,s.phone) from Supplier s " +
            "Where s.supplierId = :supplierId")
    SupplierForGetByIdDto getForById(short supplierId);
}
