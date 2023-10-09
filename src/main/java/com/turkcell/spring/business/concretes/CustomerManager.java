package com.turkcell.spring.business.concretes;

import com.turkcell.spring.business.abstracts.CustomerService;
import com.turkcell.spring.business.exceptions.BusinessException;
import com.turkcell.spring.entities.concretes.Category;
import com.turkcell.spring.entities.concretes.Customer;
import com.turkcell.spring.entities.dtos.customer.CustomerForAddDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForGetByIdDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForListingDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForUpdateDto;
import com.turkcell.spring.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {

    private CustomerRepository customerRepository;
    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void add(CustomerForAddDto customerForAddDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerForAddDto.getCustomerId());
        customer.setCity(customerForAddDto.getCity());
        customer.setAddress(customerForAddDto.getAddress());
        customer.setContactName(customerForAddDto.getContactName());
        customer.setCompanyName(customerForAddDto.getCompanyName());
        customer.setRegion(customerForAddDto.getRegion());
        customer.setCountry(customerForAddDto.getCountry());
        customerRepository.save(customer);
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteForById(id);
    }

    @Override
    public void update(CustomerForUpdateDto customerForUpdateDto) {
        Customer customerToUpdate = returnCustomerByIdIfExists(customerForUpdateDto.getCustomerId());
        customerToUpdate.setCustomerId(customerForUpdateDto.getCustomerId());
        customerToUpdate.setCity(customerForUpdateDto.getCity());
        customerToUpdate.setAddress(customerForUpdateDto.getAddress());
        customerToUpdate.setContactName(customerForUpdateDto.getContactName());
        customerToUpdate.setCompanyName(customerForUpdateDto.getCompanyName());
        customerToUpdate.setRegion(customerForUpdateDto.getRegion());
        customerToUpdate.setCountry(customerForUpdateDto.getCountry());
        customerRepository.save(customerToUpdate);
    }

    @Override
    public List<CustomerForListingDto> getAll() {
        return customerRepository.getForListing();
    }

    @Override
    public CustomerForGetByIdDto getById(String id) {
        return customerRepository.getForById(id);
    }

    private Customer returnCustomerByIdIfExists(String id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer==null)
            throw new BusinessException("Böyle bir müşteri bulunamadı.");
        return customer;
    }
}
