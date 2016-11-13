package com.nunomsousa.dao;

import java.util.List;

/**
 * This interface defines the standard operations to be performed on a Purchase Data Access Object
 */
public interface PurchaseDAO {
    /**
     * Fetches all Purchase records from the database
     * @return List of all the Purchase records
     */
    List<Purchase> getAllPurchases();

    /**
     * Retrieves a Purchase record with the given Id from the database, along with Purchase Details
     * @param id Id of the purchase to be retrieved
     * @return a PurchaseWithDetails object, containing the Purchase information along with Purchase Details
     */
    PurchaseWithDetails getPurchase(Long id);

    /**
     * Inserts the purchase into the database.
     * @param purchaseWithDetails PurchaseWithDetails object to be inserted
     * @return true if the operation was successful, otherwise returns false
     */
    Boolean insertPurchase(PurchaseWithDetails purchaseWithDetails);

    /**
     * Updates the Purchase with the same Id as the provided purchase with the new PurchaseWithDetails object
     * @param purchaseWithDetails PurchaseWithDetails object to be updated
     * @return true if the operation was successful, otherwise returns false
     */
    Boolean updatePurchase(PurchaseWithDetails purchaseWithDetails);

    /**
     * Updates the Purchase with the given Id
     * @param id Id of the purchase to be deleted
     * @return true if the operation was successful, otherwise returns false
     */
    Boolean deletePurchase(Long id);
}
