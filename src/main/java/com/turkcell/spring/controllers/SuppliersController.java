package com.turkcell.spring.controllers;

import com.turkcell.spring.business.abstracts.SupplierService;
import com.turkcell.spring.entities.dtos.supplier.SupplierForGetByIdDto;
import com.turkcell.spring.entities.dtos.supplier.SupplierForListingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("suppliers")
@RequiredArgsConstructor
public class SuppliersController {

    private final SupplierService supplierService;

    @GetMapping()
    public List<SupplierForListingDto> getAll(){
        return supplierService.getAll();
    }

    @GetMapping("getById")
    public SupplierForGetByIdDto getSupplierById(short id){
        return supplierService.getById(id);
    }
}
