package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.Customer;
import com.turkcell.spring.entities.dtos.customer.CustomerForGetByIdDto;
import com.turkcell.spring.entities.dtos.customer.CustomerForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Query(value = "Select new com.turkcell.spring.entities.dtos.customer.CustomerForListingDto" +
            "(c.contactName,c.companyName,c.address,c.city,c.region,c.country) from Customer c")
    List<CustomerForListingDto> getForListing();

    @Query(value = "Select new com.turkcell.spring.entities.dtos.customer.CustomerForGetByIdDto" +
            "(c.contactName,c.companyName,c.address,c.city,c.region,c.country) from Customer c " +
            "Where c.customerId = :customerId")
    CustomerForGetByIdDto getForById(String customerId);


    //Customer'ı silmek için önce CustomerCustomerDemo,Orders tabloları içinden silmen lazım!!
    @Query("DELETE FROM Customer WHERE customerId = :customerId")
    void deleteForById(String customerId);
}
