package com.sda.controller;

import com.sda.model.Product;
import com.sda.repository.ShopRepository;
import com.sda.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ShopController {
    private final ShopRepository repository;
    private final ShopService service;

    @Autowired
    public ShopController(ShopRepository repository, ShopService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/")
    public String displayProducts(Model model) {
        List<Product> products = repository.findAll();
        model.addAttribute("listOfAllProducts", products);
        return "index";
    }
    @RequestMapping(path ={"/","/search"})
    public String displayFoundProduct(Model model, String keyword) {
        List<Product> list = service.getByKeyword(keyword);
        model.addAttribute("listOfFoundProducts", list);
        return "search";
    }
    @GetMapping("/price-filter")
    public String sortProductsHighestFirst(Model model) {
        List<Product> sortedProducts = repository.findAllByOrderByPriceDesc();
        model.addAttribute("listOfSortedProducts", sortedProducts);
        return "sort-products";
    }

    @RequestMapping("/price-filter")
    public String filterProductsPriceHigherOrEqual(Model model, double input) {
        List<Product> productsFilteredByInputPrice = repository.findAllByPriceGreaterThanOrPriceEquals(input);
        model.addAttribute("listOfInputFilteredProducts", productsFilteredByInputPrice);
        return "filter-products";
    }
}
