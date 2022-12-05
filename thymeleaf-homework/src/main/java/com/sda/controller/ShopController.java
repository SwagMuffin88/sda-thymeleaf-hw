package com.sda.controller;

import com.sda.model.Product;
import com.sda.repository.ShopRepository;
import com.sda.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

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
//    @RequestMapping(path ={"/","/search"})
//    public String displayFoundProduct(Model model, String keyword) {
//        List<Product> list = service.getByKeyword(keyword);
//        model.addAttribute("listOfFoundProducts", list);
//        return "search";
//    }
//    @GetMapping("/search")
//    public String sortProductsHighestFirst(Model model) {
//        List<Product> sortedProducts = repository.findAllByOrderByPriceDesc();
//        model.addAttribute("listOfSortedProducts", sortedProducts);
//        return "sort-products";
//    }
//
//    @RequestMapping("/search")
//    public String filterProductsPriceHigherOrEqual(Model model, @RequestParam double input) {
//        List<Product> productsFilteredByInputPrice = repository.findAllByPriceGreaterThanOrPriceEquals(input);
//        model.addAttribute("listOfFoundProducts", productsFilteredByInputPrice);
//        return "filter-products";
//    }

    @RequestMapping("/search")
    public String searchAndSortProducts(Model model, @RequestParam(required = false) String keyword,
                                        @RequestParam(required = false) Double price_gt,
                                        @RequestParam(required = false) String sort_by,
                                        @RequestParam(required = false) String sort_direction) {
        if (keyword != null) {
            List<Product> list = service.getByKeyword(keyword);
            model.addAttribute("listOfFoundProducts", list);
        }
        if(price_gt != null && keyword == null) {
            List<Product> list = repository.findAllByPriceGreaterThanOrPriceEquals(price_gt);
            model.addAttribute("listOfFoundProducts", list);
        }
//        if (price_gt != null && !StringUtils.isEmpty(keyword)) {
//            List<Product> list = repository.findProductsByKeywordAndPriceGreaterThanOrPriceEquals(keyword, price_gt);
//            model.addAttribute("listOfFoundProducts", list);
//        }

        return "search";

    }

    // Ainus endpoint /search
    // Võtab Request parameetrid:
    //  - keyword (võtab otsingusõna)  -> DONE
    //  - sort_by (võtab väärtust vastu nagu "price")
    //  - sort_direction (võtab väärtust vastu nagu "asc" või "desc")
    //  -  price_gt (võtab double väärtust)

    // Kui väärtus on antud (ehk pole null), siis lisad seda sql päringusse,
    //      muidu kasutad mingit default väärtust.
    // Saab kombineerida search parameetreid:
    //      /search?price_gt=4&sort_by=price&sort_direction=asc
}
