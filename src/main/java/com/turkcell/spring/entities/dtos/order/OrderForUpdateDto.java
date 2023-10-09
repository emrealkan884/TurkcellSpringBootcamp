package com.turkcell.spring.entities.dtos.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderForUpdateDto {
    private short orderId;
    private LocalDate orderDate;
    private LocalDate shippedDate;
    private float freight;

    @NotNull
    @NotBlank(message = "Gemi ismi boş bırakılamaz")
    private String shipName;

    private String shipAddress;
    private String shipCity;

    @NotBlank(message = "Kargo ülkesi boş bırakılamaz")
    private String shipCountry;
}
