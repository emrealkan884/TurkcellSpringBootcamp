package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.Order;
import com.turkcell.spring.entities.dtos.order.OrderForGetByIdDto;
import com.turkcell.spring.entities.dtos.order.OrderForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Short> {
    List<Order> findByFreightGreaterThan(float freight);

    @Query(value = "SELECT * FROM orders Where freight>750", nativeQuery = true)
    List<Order> findOrdersByFreightGreaterThan750();
}
