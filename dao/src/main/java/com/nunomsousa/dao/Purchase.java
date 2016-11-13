package com.nunomsousa.dao;

import java.time.LocalDate;

public class Purchase {
    private Long id;

    private String productType;

    private LocalDate expires;

    private Details purchaseDetails;

    public Purchase(Long id, String productType, LocalDate expires, Details purchaseDetails) {
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

    public LocalDate getExpires() {
        return expires;
    }

    public void setExpires(LocalDate expires) {
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
