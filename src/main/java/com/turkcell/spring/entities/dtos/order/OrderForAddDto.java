package com.turkcell.spring.entities.dtos.order;

import com.turkcell.spring.entities.dtos.orderDetail.OrderDetailForAddDto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderForAddDto {

    @PastOrPresent(message = "{shippedDateMustBePastOrPresentDate}")
    private LocalDate shippedDate;
    @Positive(message = "{freightShouldBeGreaterThan0}")
    private float freight;
    private String shipAddress;
    private String shipCountry;
    private String shipName;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;

    @NotNull(message = "{customerIdCantBeEmpty}")//Normalde giriş yapmış kullanıcı otomatik id'si alınır..
    private String customerId;

    @NotNull(message = "{employeeIdCantBeEmpty}")
    private short employeeId;
    @NotNull(message = "{shipperIdCantBeEmpty}")
    private short shipperId;

    @Future(message = "{requiredDateShouldBeInTheFuture}")
    private LocalDate requiredDate;

    private List<OrderDetailForAddDto> items;
}
