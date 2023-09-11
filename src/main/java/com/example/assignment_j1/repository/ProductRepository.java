package com.example.assignment_j1.repository;

import com.example.assignment_j1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findProductByCode(String code);
    void deleteProductByCode(String code);
}
