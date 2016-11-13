package com.nunomsousa.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PurchaseWithDetails extends Purchase {
    @JsonProperty("purchaseDetails")
    private Details purchaseDetails;

    public PurchaseWithDetails() {
        //Empty constructor
    }

    public PurchaseWithDetails(Long id, String productType, LocalDateTime expires, Details purchaseDetails) {
        super(id, productType, expires);
        this.purchaseDetails = purchaseDetails;
    }

    public Details getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(Details purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
}
