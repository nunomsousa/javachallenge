package com.nunomsousa.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Purchase {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("productType")
    private String productType;

    @JsonProperty("expires")
    private LocalDateTime expires;

    @JsonProperty("details")
    private Details purchaseDetails;

    public Purchase() {
        //Default constructor
    }

    public Purchase(Long id, String productType, LocalDateTime expires, Details purchaseDetails) {
        this.id = id;
        this.productType = productType;
        this.expires = expires;
        this.purchaseDetails = purchaseDetails;
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

    public Details getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(Details purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", productType='" + productType + '\'' +
                ", expires=" + expires +
                ", purchaseDetails=" + purchaseDetails +
                '}';
    }
}
