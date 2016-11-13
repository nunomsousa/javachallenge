package com.nunomsousa.rest;

import com.nunomsousa.dao.Details;
import com.nunomsousa.dao.Purchase;
import com.nunomsousa.dao.impl.PurchaseDAO;
import com.nunomsousa.dao.impl.PurchaseDAOImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/webapi")
public class JavaChallengeWebAPI {
    private static PurchaseDAO purchaseDAO;
    static {
        purchaseDAO = new PurchaseDAOImpl();
        Purchase purchase = new Purchase(0L, "Type", LocalDate.now(), new Details(0L, "Desc", 1, 0.5));
        purchaseDAO.insertPurchase(purchase);
    }

    @GET
    @Path("fetch_valid_purchases")
    @Produces(MediaType.TEXT_PLAIN)
    public String fetchValidPurchases() {
        StringBuilder sb = new StringBuilder();
        for (Purchase purchase : purchaseDAO.getAllPurchases()) {
            sb.append(purchase.toString());
            sb.append('\n');
        }
        return sb.toString();
    }
}
