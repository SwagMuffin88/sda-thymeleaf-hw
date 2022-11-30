package com.sda.controller;

import com.sda.model.Product;
import com.sda.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShopController {
    private final ShopRepository repository;

    @Autowired
    public ShopController(ShopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String displayProducts(Model model) {
        List<Product> products = repository.findAll();
        model.addAttribute("listOfAllProducts", products);
        return "index";
    }
}
