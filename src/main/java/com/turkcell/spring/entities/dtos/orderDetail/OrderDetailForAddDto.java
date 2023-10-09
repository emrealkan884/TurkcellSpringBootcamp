package com.turkcell.spring.entities.dtos.orderDetail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailForAddDto {
    private short productId;
    private short quantity;
}
