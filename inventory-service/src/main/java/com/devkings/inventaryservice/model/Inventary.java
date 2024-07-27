package com.devkings.inventaryservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_inventary")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Inventary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
