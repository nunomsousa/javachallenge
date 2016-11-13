package com.nunomsousa.dao;

import java.util.List;

public interface PurchaseDAO {
    List<Purchase> getAllPurchases();

    Purchase getPurchase(Long id);

    void insertPurchase(Purchase purchase);

    void updatePurchase(Purchase purchase);

    void deletePurchase(Long id);
}
