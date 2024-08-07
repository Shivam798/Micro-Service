package com.devkings.orderservice.service;

import com.devkings.orderservice.dto.InventoryResponse;
import com.devkings.orderservice.dto.OrderLineItemsdto;
import com.devkings.orderservice.dto.OrderRequest;
import com.devkings.orderservice.model.Order;
import com.devkings.orderservice.model.OrderLineItems;
import com.devkings.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrder_number(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsdtos()
                .stream()
                .map(this::maptoDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

//        Call inventory service to check the presence of the item
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventary-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        boolean allProductInStock =Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        if(allProductInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is not in stock , please try again later.");
        }
    }

    private OrderLineItems maptoDto(OrderLineItemsdto orderLineItemsdto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsdto.getPrice());
        orderLineItems.setQunatity(orderLineItemsdto.getQunatity());
        orderLineItems.setSkuCode(orderLineItemsdto.getSkuCode());
        return orderLineItems;
    }
}
