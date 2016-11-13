package com.nunomsousa.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO class that holds Details data, related to a Purchase
 */
public class Details {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("value")
    private Double value;

    public Details() {
        //Default constructor
    }

    public Details(Long id, String description, Integer quantity, Double value) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", value=" + value +
                '}';
    }
}
