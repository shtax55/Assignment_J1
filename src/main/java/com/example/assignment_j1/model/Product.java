package com.example.assignment_j1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "code",unique = true, length = 10)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price_eur")
    private Double priceEur;

    @Column(name = "price_usd")
    private Double priceUsd;

    @Column(name = "description")
    private String description;

    @Column(name = "is_available")
    private Boolean isAvailable;

    public Product(String code, String name, Double priceEur, String description, Boolean isAvailable) {
        this.code = code;
        this.name = name;
        this.priceEur = priceEur;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public Product() {

    }

    @JsonProperty("isAvailable") //spring boot can't convert Boolean to JSON correctly -> https://rakeshnarang.medium.com/what-the-hell-is-wrong-with-spring-boot-cant-convert-boolean-to-json-correctly-solution-aa5a418dd341
    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(Double price_eur) {
        this.priceEur = price_eur;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double price_usd) {
        this.priceUsd = price_usd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
