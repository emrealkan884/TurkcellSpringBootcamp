package com.turkcell.spring.controllers;

import com.turkcell.spring.business.abstracts.CustomerService;
import com.turkcell.spring.entities.dtos.customer.CustomerForAddDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForGetByIdDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForListingDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomersController {
    private final CustomerService customerService;
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid CustomerForAddDto customerForAddDto){
        customerService.add(customerForAddDto);
        return new ResponseEntity("Müşteri eklendi", HttpStatus.CREATED);
    }

    public void delete(String id){
        //CustomerRepository'i tamamla
    }
    @PutMapping("update")
    public ResponseEntity update(@RequestBody @Valid CustomerForUpdateDto customerForUpdateDto){
        customerService.update(customerForUpdateDto);
        return new ResponseEntity("Müşteri güncellendi", HttpStatus.CREATED);
    }
    @GetMapping()
    public List<CustomerForListingDto> getAll(){
        return customerService.getAll();
    }

    @GetMapping("getById")
    public CustomerForGetByIdDto getById(String id){
       return customerService.getById(id);
    }
}
