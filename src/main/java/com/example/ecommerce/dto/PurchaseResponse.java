package com.example.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {
    // or instead of declaring it as final we can use
    // @Notnull annotation near @Data annotation
    private final String orderTrackingNumber;
}
