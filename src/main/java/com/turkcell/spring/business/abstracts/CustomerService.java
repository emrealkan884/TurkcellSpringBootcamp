package com.turkcell.spring.business.abstracts;

import com.turkcell.spring.entities.dtos.category.CategoryForAddDto;
import com.turkcell.spring.entities.dtos.category.CategoryForGetByIdDto;
import com.turkcell.spring.entities.dtos.category.CategoryForListingDto;
import com.turkcell.spring.entities.dtos.category.CategoryForUpdateDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForAddDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForGetByIdDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForListingDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForUpdateDto;

import java.util.List;

public interface CustomerService {
    void add(CustomerForAddDto customerForAddDto);
    void delete(String id);
    void update(CustomerForUpdateDto customerForUpdateDto);
    List<CustomerForListingDto> getAll();
    CustomerForGetByIdDto getById(String id);
}
