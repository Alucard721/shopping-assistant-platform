package com.niit.search.repository;

import com.niit.search.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>, PagingAndSortingRepository<Product, Integer> {

	List<Product> findProductsByProductId(String productId);

	@Modifying
	@Query(value = "UPDATE Product p SET p.price= :price,p.productImg=:productImg,p.updateTime=:updateTime WHERE p.productId= :id")
	int updateByProductId(@Param("id") String id, @Param("price") String price, @Param("productImg") String productImg, @Param("updateTime") String updateTime);
}
