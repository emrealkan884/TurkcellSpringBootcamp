package com.turkcell.spring.entities.concretes;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "company_name")
    private String companyName;

    @Column (name = "contact_name")
    private String contactName;

    @Column (name = "contact_title")
    private String contactTitle;

    @Column (name = "address")
    private String address;

    @Column (name = "city")
    private String city;

    @Column (name = "region")
    private String region;

    @Column (name = "postal_code")
    private String postalCode;

    @Column (name = "country")
    private String country;

    @Column (name = "phone")
    private String phone;

    @Column (name = "fax")
    private String fax;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy= "customer")
    private List<CustomerCustomerDemo> customerCustomerDemo;
}
