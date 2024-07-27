package com.devkings.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrderRequest {
    private List<OrderLineItemsdto> orderLineItemsdtos;
}
