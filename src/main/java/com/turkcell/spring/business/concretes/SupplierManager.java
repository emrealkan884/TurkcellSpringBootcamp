package com.turkcell.spring.business.concretes;

import com.turkcell.spring.business.abstracts.SupplierService;
import com.turkcell.spring.entities.dtos.supplier.SupplierForGetByIdDto;
import com.turkcell.spring.entities.dtos.supplier.SupplierForListingDto;
import com.turkcell.spring.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierManager implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public List<SupplierForListingDto> getAll() {
        return supplierRepository.getForListing();
    }

    @Override
    public SupplierForGetByIdDto getById(short id) {
        return supplierRepository.getForById(id);
    }
}
