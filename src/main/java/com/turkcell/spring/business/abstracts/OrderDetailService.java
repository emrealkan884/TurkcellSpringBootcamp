package com.turkcell.spring.business.abstracts;

import com.turkcell.spring.entities.concretes.Order;
import com.turkcell.spring.entities.dtos.orderDetail.OrderDetailForAddDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderDetailService {
    void addItemsToOrder(Order order, List<OrderDetailForAddDto> items);
}
