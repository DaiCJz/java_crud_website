package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer product1;
    private Integer product2;
    private Integer product3;
    private String pickup;
    private String address;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct1() {
        return product1;
    }

    public void setProduct1(Integer product1) {
        this.product1 = product1;
    }

    public Integer getProduct2() {
        return product2;
    }

    public void setProduct2(Integer product2) {
        this.product2 = product2;
    }

    public Integer getProduct3() {
        return product3;
    }

    public void setProduct3(Integer product3) {
        this.product3 = product3;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}