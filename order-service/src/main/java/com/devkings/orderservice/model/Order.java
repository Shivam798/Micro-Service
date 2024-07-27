package com.devkings.orderservice.model;


import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="order_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String order_number;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

}
