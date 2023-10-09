package com.turkcell.spring.business.concretes;

import com.turkcell.spring.business.abstracts.OrderDetailService;
import com.turkcell.spring.business.abstracts.ProductService;
import com.turkcell.spring.entities.concretes.Order;
import com.turkcell.spring.entities.concretes.OrderDetail;
import com.turkcell.spring.entities.concretes.Product;
import com.turkcell.spring.entities.dtos.orderDetail.OrderDetailForAddDto;
import com.turkcell.spring.entities.dtos.product.ProductForGetByIdDto;
import com.turkcell.spring.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailManager implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;

    public OrderDetailManager(OrderDetailRepository orderDetailRepository,ProductService productService) {
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
    }
    @Override
    public void addItemsToOrder(Order order, List<OrderDetailForAddDto> items) {
       for (OrderDetailForAddDto item : items){
           OrderDetail orderDetail = OrderDetail.builder()
                   .unitPrice(productService.getById(item.getProductId()).getUnitPrice())
                   .discount(0)
                   .quantity(productService.checkUnitsInStockAndQuantityAndReturnQuantity(item.getProductId(),item.getQuantity()))
                   .product(Product.builder().productId(item.getProductId()).build())
                   .order(Order.builder().orderId(order.getOrderId()).build())
                   .build();
           orderDetailRepository.save(orderDetail);

       }
    }
}
