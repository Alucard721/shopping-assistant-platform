package com.niit.management.repository;

import com.niit.management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>, CrudRepository<Product,Integer> {

	@Query(value = "SELECT * FROM product p WHERE p.spare_field3 != ''",nativeQuery = true)
	List<Product> findAllInvalidProducts();
}
