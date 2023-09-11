package com.example.assignment_j1.controller;

import com.example.assignment_j1.model.Product;
import com.example.assignment_j1.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void shouldCreateProduct() throws Exception {
        Product product = new Product("testProd01","Product01", 12.35,"Product 01", true);
        mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnProduct() throws Exception {
        Product product = new Product("testProd01","Product01", 12.35,"Product 01", true);

        when(productService.getProduct("testProd01")).thenReturn(product);

        mockMvc.perform(get("/api/products/{code}", "testProd01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("testProd01"))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.priceEur").value(product.getPriceEur()))
                .andExpect(jsonPath("$.priceUsd").value(product.getPriceUsd()))
                .andExpect(jsonPath("$.isAvailable").value(product.getAvailable()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundProduct() throws Exception {
        when(productService.getProduct("noProd0001")).thenReturn(null);
        mockMvc.perform(get("/api/tutorials/{id}", "noProd0001"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldReturnListOfProducts() throws Exception {
        List<Product> products = new ArrayList<>(
                Arrays.asList(
                        new Product("testProd01","Product01", 12.35,"Product 01", true),
                        new Product("testProd02","Product02", 2.35,"Product 02", true),
                        new Product("testProd03","Product03", 13.98,"Product 03", true)));

        when(productService.getAllProducts()).thenReturn(products);
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(products.size()))
                .andDo(print());
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        Product product = new Product("testProd01","Product01", 12.35,"Product 01", true);
        Product updatedProduct = new Product("testProd01", "Updated Product01", 12.35,"updated product 1",true);

        when(productService.getProduct("testProd01")).thenReturn(product);
        when(productService.updateProduct(eq("testProd01"), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/{id}", "testProd01").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("testProd01"))
                .andExpect(jsonPath("$.name").value(updatedProduct.getName()))
                .andExpect(jsonPath("$.description").value(updatedProduct.getDescription()))
                .andExpect(jsonPath("$.priceEur").value(updatedProduct.getPriceEur()))
                .andExpect(jsonPath("$.priceUsd").value(updatedProduct.getPriceUsd()))
                .andExpect(jsonPath("$.isAvailable").value(updatedProduct.getAvailable()))
                .andDo(print());
    }


    @Test
    void shouldDeleteProduct() throws Exception {

        doNothing().when(productService).deleteProduct("testProd01");
        mockMvc.perform(delete("/api/products/{id}", "testProd01"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}