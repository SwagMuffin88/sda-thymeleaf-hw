package com.sda.service;

import com.sda.model.Product;
import com.sda.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository repository;

    public List<Product> getByKeyword(String keyword){
        return repository.findProductsByKeyword(keyword);
    }
}
