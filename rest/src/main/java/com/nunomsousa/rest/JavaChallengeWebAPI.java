package com.nunomsousa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nunomsousa.dao.Purchase;
import com.nunomsousa.dao.PurchaseDAO;
import com.nunomsousa.dao.impl.PurchaseDAOImpl;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/purchases")
public class JavaChallengeWebAPI {
    private static PurchaseDAO purchaseDAO;

    private static ObjectMapper mapper;

    static {
        purchaseDAO = new PurchaseDAOImpl();

        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchValidPurchases() throws JsonProcessingException {
        List<Purchase> purchases = purchaseDAO.getAllPurchases();
        List<Purchase> validPurchases = purchases.stream()
                .filter(purchase -> LocalDateTime.now().isBefore(purchase.getExpires()))
                .collect(Collectors.toList());

        return mapper.writeValueAsString(validPurchases);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testPurchaseJson(@PathParam("id") String stringId, @Context final HttpServletResponse response) {
        Long id = Long.valueOf(stringId);
        Purchase purchase = purchaseDAO.getPurchase(id);

        if (purchase == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        String jsonResponse;
        try {
            jsonResponse = mapper.writeValueAsString(purchase);
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(jsonResponse).build();
    }

    @POST
    @Path("insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String insert(String purchaseJson) throws IOException {
        Purchase purchase = mapper.readValue(purchaseJson, Purchase.class);
        purchaseDAO.insertPurchase(purchase);
        return "Success";
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(String purchaseJson) throws IOException {
        Purchase purchase = mapper.readValue(purchaseJson, Purchase.class);
        purchaseDAO.updatePurchase(purchase);
        return "Success";
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") String stringId) {
        Long id = Long.valueOf(stringId);
        purchaseDAO.deletePurchase(id);
        return "Success";
    }
}
