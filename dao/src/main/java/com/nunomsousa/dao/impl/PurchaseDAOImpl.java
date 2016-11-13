package com.nunomsousa.dao.impl;

import com.nunomsousa.dao.Purchase;
import com.nunomsousa.dao.PurchaseDAO;
import com.nunomsousa.dao.PurchaseWithDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Example of an implementation of a PurchaseDAO, to be used for testing purposes
 */
public class PurchaseDAOImpl implements PurchaseDAO {
    //The map that will contain all Purchase records
    private static Map<Long, PurchaseWithDetails> purchaseMap;

    //The counter that will be used to assign an Id to an inserted Purchase record
    private static AtomicLong currentIndex;

    static {
        //Initialization of the static variables
        purchaseMap = new ConcurrentHashMap<>();
        currentIndex = new AtomicLong(0L);
    }

    public PurchaseDAOImpl() {
    }

    /**
     * Fetches all Purchase records from the database
     * @return List of all the Purchase records
     */
    @Override
    public List<Purchase> getAllPurchases() {
        return new ArrayList<>(purchaseMap.values());
    }

    /**
     * Retrieves a Purchase record with the given Id from the database, along with Purchase Details
     * @param id Id of the purchase to be retrieved
     * @return a PurchaseWithDetails object, containing the Purchase information along with Purchase Details
     */
    @Override
    public PurchaseWithDetails getPurchase(Long id) {
        return purchaseMap.get(id);
    }

    /**
     * Inserts the purchase into the database.
     * @param purchaseWithDetails PurchaseWithDetails object to be inserted
     * @return true if the operation was successful, otherwise returns false
     */
    @Override
    public Boolean insertPurchase(PurchaseWithDetails purchaseWithDetails) {
        Long id = currentIndex.getAndIncrement();
        purchaseWithDetails.setId(id);
        purchaseMap.put(id, purchaseWithDetails);
        return true;
    }

    /**
     * Updates the Purchase with the same Id as the provided purchase with the new PurchaseWithDetails object
     * @param purchaseWithDetails PurchaseWithDetails object to be updated
     * @return true if the operation was successful, otherwise returns false
     */
    @Override
    public Boolean updatePurchase(PurchaseWithDetails purchaseWithDetails) {
        if (!purchaseMap.containsKey(purchaseWithDetails.getId())) {
            return false;
        }
        purchaseMap.put(purchaseWithDetails.getId(), purchaseWithDetails);
        return true;
    }

    /**
     * Updates the Purchase with the given Id
     * @param id Id of the purchase to be deleted
     * @return true if the operation was successful, otherwise returns false
     */
    @Override
    public Boolean deletePurchase(Long id) {
        if (!purchaseMap.containsKey(id)) {
            return false;
        }
        purchaseMap.remove(id);
        return true;
    }
}
