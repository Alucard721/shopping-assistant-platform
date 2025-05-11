package com.niit.interact.serviceImpl;

import com.niit.interact.entity.Product;
import com.niit.interact.repository.ProductRepository;
import com.niit.interact.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAllProducts() {

        List<Product> products = productRepository.findProductsByIdBetween(25,45);
        return products;

    }

    @Override
    public List<Product> randomSelect() {
        List<Product> products = productRepository.randomGenerate();
        return products;
    }

    @Override
    public List<Product> randomSelect2() {
        List<Product> products = productRepository.randomGenerate2();
        return products;
    }

    @Override
    public List<Product> popularSelect() {
        List<Product> products = productRepository.popularGenerate();
        return products;
    }

}
