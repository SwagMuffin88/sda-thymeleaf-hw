package com.sda.repository;

import com.sda.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();
    //Custom query
    @Query(value = "select * from product p where p.item like %:keyword% ", nativeQuery = true)
    List<Product> findProductsByKeyword(@Param("keyword") String keyword);
    List<Product> findAllByOrderByPriceDesc();

    @Query(value = "select * from product p where p.price > :price_gt", nativeQuery = true)
    List<Product> findAllByPriceGreaterThanOrPriceEquals(@Param("price_gt") Double price_gt);

    @Query(value = "select * from product p where p.item like %:keyword% and p.price > :input", nativeQuery = true)
    List<Product> findProductsByKeywordAndPriceGreaterThanOrPriceEquals(
            @Param("keyword") String keyword, @Param("input") double input);
}
