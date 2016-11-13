package com.nunomsousa.dao.impl;

import com.nunomsousa.dao.Purchase;
import com.nunomsousa.dao.PurchaseDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PurchaseDAOImpl implements PurchaseDAO {
    private static Map<Long, Purchase> purchaseMap;

    private static Long currentIndex;

    static {
        purchaseMap = new ConcurrentHashMap<>();
        currentIndex = 0L;
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
        purchase.setId(currentIndex);
        purchaseMap.put(currentIndex++, purchase);
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        purchaseMap.put(purchase.getId(), purchase);
    }

    @Override
    public void deletePurchase(Long id) {
        purchaseMap.remove(id);
    }
}
