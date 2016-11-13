package com.nunomsousa.dao.impl;

import com.nunomsousa.dao.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PurchaseDAOImpl implements PurchaseDAO {
    private static Map<Long, Purchase> purchaseMap;

    static {
        purchaseMap = new ConcurrentHashMap<>();
    }

    public PurchaseDAOImpl() {
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return new ArrayList<>(purchaseMap.values());
    }

    @Override
    public Purchase getPurchase(Long id) {
        return purchaseMap.get(id);
    }

    @Override
    public void insertPurchase(Purchase purchase) {
        purchaseMap.put(purchase.getId(), purchase);
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        purchaseMap.put(purchase.getId(), purchase);
    }

    @Override
    public void deletePurchase(Purchase purchase) {
        purchaseMap.remove(purchase.getId());
    }
}
