package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Short> {
}
