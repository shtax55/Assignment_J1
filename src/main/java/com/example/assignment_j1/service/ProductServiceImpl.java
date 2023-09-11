package com.example.assignment_j1.service;

import com.example.assignment_j1.model.Product;
import com.example.assignment_j1.model.USDRate;
import com.example.assignment_j1.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.tools.json.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getProduct(String code) {
        return productRepository.findProductByCode(code);
    }

    @Override
    public Product saveProduct(Product product) throws ParseException, JsonProcessingException {
        product.setPriceUsd(calculateUSD(product.getPriceEur()));
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String code, Product product) throws ParseException, JsonProcessingException {
        Product existingProduct = productRepository.findProductByCode(code);
        existingProduct.setAvailable(product.getAvailable());
        existingProduct.setCode(product.getCode());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setName(product.getName());
        existingProduct.setPriceEur(product.getPriceEur());
        existingProduct.setPriceUsd(calculateUSD(product.getPriceEur()));
        return productRepository.save(existingProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(String code) {
        productRepository.deleteProductByCode(code);
    }

    private Double getUSD() throws ParseException, JsonProcessingException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.hnb.hr/tecajn-eur/v3?valuta=USD"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String json = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        USDRate[] usdRate = objectMapper.readValue(json, USDRate[].class);


        return parseDouble(usdRate[0].getSrednjiTecaj().replace(",","."));
    }

    private Double calculateUSD(Double eur) throws ParseException, JsonProcessingException {
        return (eur * getUSD());
    }


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
