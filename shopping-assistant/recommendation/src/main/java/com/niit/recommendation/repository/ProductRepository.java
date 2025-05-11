package com.niit.recommendation.repository;

import com.niit.recommendation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product,Integer>, CrudRepository<Product,Integer> {

	@Modifying
	@Query("update Product p set p.categoryName=?1 where p.id = 10")
	void updateProduct(String s);
}
