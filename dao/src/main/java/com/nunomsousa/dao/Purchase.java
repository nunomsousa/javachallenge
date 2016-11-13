package com.nunomsousa.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * POJO class that holds data retrieved from the Purchase DAO
 */
public class Purchase {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("productType")
    private String productType;

    @JsonProperty("expires")
    private LocalDateTime expires;

    public Purchase() {
        //Default constructor
    }

    public Purchase(Long id, String productType, LocalDateTime expires) {
        this.id = id;
        this.productType = productType;
        this.expires = expires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", expires=" + expires +
                '}';
    }
}
