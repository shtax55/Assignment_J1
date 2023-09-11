package com.example.assignment_j1.controller;

import com.example.assignment_j1.model.Product;
import com.example.assignment_j1.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.jooq.tools.json.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final ProductService productService;

    @Operation(
            summary = "Retrieve all products",
            description = "Get all products with",
            tags = { "products", "get" })
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        try {
            List<Product> products = productService.getAllProducts();

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Retrieve a product",
            description = "Get product with its unique product code",
            tags = { "products", "get" })
    @GetMapping("/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        try {
            Product product = productService.getProduct(code);

            if (product == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Create product",
            description = "Creating new product",
            tags = { "products", "post", "create" })
    @PostMapping(produces="application/json")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ParseException, JsonProcessingException {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update product",
            description = "Update specific product with new values",
            tags = { "products", "put", "update" })
    @PutMapping("/{code}")
    public ResponseEntity<Product> updateProduct(@PathVariable String code, @RequestBody Product product) throws ParseException, JsonProcessingException {
        if (productService.getProduct(code) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productService.updateProduct(code, product), HttpStatus.CREATED);
        }
    }

    @Operation(
            summary = "Delete product",
            description = "Delete a specific product",
            tags = { "products", "delete" })
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteProduct(@PathVariable String code) {
       if (productService.getProduct(code) == null) {
           return new ResponseEntity<>("Product deleted not found!", HttpStatus.NO_CONTENT);
       } else {
           productService.deleteProduct(code);
           return new ResponseEntity<>("Product deleted successfully!", HttpStatus.OK);
       }
    }

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}

