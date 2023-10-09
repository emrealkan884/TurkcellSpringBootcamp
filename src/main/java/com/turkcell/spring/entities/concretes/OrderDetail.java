package com.turkcell.spring.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private short id;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "quantity")
    private short quantity;

    @Column(name = "discount")
    private float discount;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    //@JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    //@JsonIgnore
    private Product product;
}
