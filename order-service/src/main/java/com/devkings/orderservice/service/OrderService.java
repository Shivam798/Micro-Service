package com.devkings.orderservice.service;

import com.devkings.orderservice.dto.OrderLineItemsdto;
import com.devkings.orderservice.dto.OrderRequest;
import com.devkings.orderservice.model.Order;
import com.devkings.orderservice.model.OrderLineItems;
import com.devkings.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrder_number(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsdtos()
                .stream()
                .map(this::maptoDto)
                .toList();
        order.setOrderLineItemsListl(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems maptoDto(OrderLineItemsdto orderLineItemsdto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsdto.getPrice());
        orderLineItems.setQunatity(orderLineItemsdto.getQunatity());
        orderLineItems.setSkuCode(orderLineItemsdto.getSkuCode());
        return orderLineItems;
    }
}
