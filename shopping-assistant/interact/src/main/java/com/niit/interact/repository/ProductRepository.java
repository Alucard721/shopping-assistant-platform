package com.niit.interact.repository;

import com.niit.interact.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	List<Product> findProductsByIdBetween(Integer id1,Integer id2);

	@Query(value = "select * from product order by rand() limit 8 ", nativeQuery = true)
	List<Product> randomGenerate();

	@Query(value = "select * from product order by rand() limit 20 ", nativeQuery = true)
	List<Product> randomGenerate2();

	@Query(value = "select * from product order by product_score desc limit 30 ", nativeQuery = true)
	List<Product> popularGenerate();

}
