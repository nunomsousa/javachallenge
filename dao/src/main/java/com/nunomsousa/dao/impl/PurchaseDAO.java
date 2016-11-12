package com.nunomsousa.dao.impl;

import com.nunomsousa.dao.Purchase;

import java.util.List;

public interface PurchaseDAO {
    List<Purchase> getAllPurchases();
    Purchase getPurchase(Long id);
    void updatePurchase(Purchase purchase);
    void deletePurchase(Purchase purchase);
}
