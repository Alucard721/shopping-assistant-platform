package com.niit.crawler.service;

import com.niit.crawler.entity.Category;
import com.niit.crawler.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * @Description: 批量保存商品种类

	 * @param categories
	 * @return void
	 */
	@Transactional
	public void saveCategory(List<Category> categories) {
		categoryRepository.saveAll(categories);
	}

	/**
	 * @return List<String>
	 * @Description: 查询所有商品种类，供商品爬取搜索用
	 */
	@Transactional
	public List<String> findAllCategory() {
		List<Category> categories = categoryRepository.findCategoriesByIdBetween(1,1190);
		List<String> keys = new ArrayList<String>();
		for (Category category : categories) {
			keys.add(category.getCategory3Name());
		}
		return keys;
	}
}
