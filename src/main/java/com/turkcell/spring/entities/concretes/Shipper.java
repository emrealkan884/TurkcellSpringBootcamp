package com.turkcell.spring.entities.concretes;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="shippers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipper_id")
    private short shipperId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "shipper")
    private List<Order> order;
}
