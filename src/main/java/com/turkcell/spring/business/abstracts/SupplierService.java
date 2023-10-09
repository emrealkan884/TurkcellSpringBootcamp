package com.turkcell.spring.business.abstracts;


import com.turkcell.spring.entities.dtos.supplier.SupplierForGetByIdDto;
import com.turkcell.spring.entities.dtos.supplier.SupplierForListingDto;

import java.util.List;

public interface SupplierService {

    List<SupplierForListingDto> getAll();
    SupplierForGetByIdDto getById(short id);
}
