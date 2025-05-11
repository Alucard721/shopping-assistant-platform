package com.niit.crawler.repository;

import com.niit.crawler.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>, PagingAndSortingRepository<Category, Integer> {

	public Category findCategoryByCategory3Name(String name);

	List<Category> findCategoriesByIdBetween(Integer id1,Integer id2);
}
