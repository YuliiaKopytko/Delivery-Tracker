package com.kopytko.orderservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "payment_status")
    private String paymentStatus;
}
