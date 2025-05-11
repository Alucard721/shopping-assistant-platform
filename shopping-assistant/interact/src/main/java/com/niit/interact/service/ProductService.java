package com.niit.interact.service;

import com.niit.interact.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer id);

    List<Product> findAllProducts();

    List<Product> randomSelect();

    List<Product> randomSelect2();

    List<Product> popularSelect();

}
