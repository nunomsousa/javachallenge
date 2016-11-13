package com.nunomsousa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nunomsousa.dao.Purchase;
import com.nunomsousa.dao.PurchaseDAO;
import com.nunomsousa.dao.PurchaseWithDetails;
import com.nunomsousa.dao.impl.PurchaseDAOImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class defines the resources that the API will handle, namely the retrieval of valid purchase details, and the storing/updating of purchase records
 */
@Path("/purchases")
public class JavaChallengeWebAPI {
    private static PurchaseDAO purchaseDAO;

    private static ObjectMapper mapper;

    static {
        purchaseDAO = new PurchaseDAOImpl();

        //Initialize the ObjectMapper, with a module that (de)serializes JavaTime objects into JSON
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    /**
     * This method fetches all Purchase records from the database, filters the valid ones, and then gets all Purchase Details for each valid purchase,
     * returning a list with all Purchase Details of valid Purchases.
     *
     * @return a JSON object containing a list of Purchase Details of all valid Purchases
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchValidPurchases() {
        try {
            List<Purchase> purchases = purchaseDAO.getAllPurchases();
            Set<Long> validPurchaseIds = purchases.stream()
                    .filter(purchase -> LocalDateTime.now().isBefore(purchase.getExpires()))
                    .map(Purchase::getId)
                    .collect(Collectors.toSet());

            List<PurchaseWithDetails> validPurchasesWithDetails = new ArrayList<>(validPurchaseIds.size());
            for (Long purchaseId : validPurchaseIds) {
                validPurchasesWithDetails.add(purchaseDAO.getPurchase(purchaseId));
            }

            return Response.ok(mapper.writeValueAsString(validPurchasesWithDetails)).build();
        } catch (Exception e) {
            return internalServerError();
        }
    }

    /**
     * This method handles GET requests, which fetch the purchase with the provided Id from the database and return it in JSON format
     *
     * @param stringId the ID of the purchase to be fetched
     * @return a Response with status code 200 and the requested purchase in JSON format, or an Internal Server Error Response if an error occurred
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response getPurchaseById(@PathParam("id") String stringId) {
        Long id = Long.valueOf(stringId);
        Purchase purchase = purchaseDAO.getPurchase(id);

        if (purchase == null) {
            return notFound();
        }

        String jsonResponse;
        try {
            jsonResponse = mapper.writeValueAsString(purchase);
        } catch (JsonProcessingException e) {
            return internalServerError();
        }
        return Response.ok(jsonResponse).build();
    }

    /**
     * This method handles INSERT requests, which insert the provided purchaseJson in the database.
     * The purchaseJson should not have an Id, since this will be assigned by the database. The Id will be ignored if it is present
     *
     * @param purchaseJson a Purchase object in JSON format
     * @return a Response with status code 200 if the request was completed successfully, or an Internal Server Error Response if an error occurred
     */
    @POST
    @Path("insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insert(String purchaseJson) {
        try {
            PurchaseWithDetails purchaseWithDetails = mapper.readValue(purchaseJson, PurchaseWithDetails.class);
            purchaseDAO.insertPurchase(purchaseWithDetails);
        } catch (Exception e) {
            return internalServerError();
        }
        return Response.ok("").build();
    }

    /**
     * This method handles UPDATE requests, which update the database with the provided purchaseJson
     *
     * @param purchaseJson a Purchase object in JSON format
     * @return a Response with status code 200 if the request was completed successfully, or an Internal Server Error Response if an error occurred
     */
    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(String purchaseJson) {
        Boolean result;
        try {
            PurchaseWithDetails purchaseWithDetails = mapper.readValue(purchaseJson, PurchaseWithDetails.class);
            result = purchaseDAO.updatePurchase(purchaseWithDetails);
        } catch (Exception e) {
            return internalServerError();
        }
        return result ? Response.ok("").build() : notFound();
    }

    /**
     * This method handles DELETE requests, which delete the purchase with id stringId from the database
     *
     * @param stringId the ID of the purchase to be deleted
     * @return a Response with status code 200 if the request was completed successfully, or an Internal Server Error Response if an error occurred
     */
    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") String stringId) {
        Boolean result;
        try {
            Long id = Long.valueOf(stringId);
            result = purchaseDAO.deletePurchase(id);
        } catch (Exception e) {
            return internalServerError();
        }
        return result ? Response.ok("").build() : notFound();
    }

    /**
     * This method returns a generic Not Found response
     *
     * @return a generic Not Found Response
     */
    private static Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).entity("Could not find the provided information in the database.").type(MediaType.TEXT_PLAIN).build();
    }

    /**
     * This method returns a generic Internal Server Error response
     *
     * @return a generic Internal Server Error Response
     */
    private static Response internalServerError() {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An unexpected error occurred while processing the request.").type(MediaType.TEXT_PLAIN).build();
    }
}
