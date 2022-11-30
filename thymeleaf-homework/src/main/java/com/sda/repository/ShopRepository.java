package com.sda.repository;

import com.sda.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    Product findByItemName(String itemName);
    List<Product> findByPrice(long id);
}
