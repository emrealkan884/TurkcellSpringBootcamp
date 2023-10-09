package com.turkcell.spring.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer_customer_demo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCustomerDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name="customer_type_id")
    private CustomerDemographic customerDemographic;
}
