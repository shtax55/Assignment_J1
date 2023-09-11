package com.example.assignment_j1.service;

import com.example.assignment_j1.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.jooq.tools.json.ParseException;

import java.util.List;

public interface ProductService {
     List<Product> getAllProducts();
     Product getProduct(String code);
     Product saveProduct(Product product) throws ParseException, JsonProcessingException;
     Product updateProduct(String code, Product product) throws ParseException, JsonProcessingException;
     void deleteProduct(String code);
}
