package com.niit.interact.controller;

import com.niit.interact.entity.Product;
import com.niit.interact.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/get")
    public List<Product> getAllPro(){
        return productService.findAllProducts();
    }

    @GetMapping("/random")
    public List<Product> getRanPro(){
        return productService.randomSelect();
    }

    @GetMapping("/random2")
    public List<Product> getRanPro2(){
        return productService.randomSelect2();
    }

    @GetMapping("/popular")
    public List<Product> getPopular(){
        return productService.popularSelect();
    }

}
