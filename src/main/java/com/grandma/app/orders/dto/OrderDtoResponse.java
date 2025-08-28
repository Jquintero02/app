package com.grandma.app.orders.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDtoResponse {
    private String uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private String productUuid;
    private int quantity;
    private String extraInformation;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;
    private boolean delivered;
    private LocalDateTime deliveredDate;
}
