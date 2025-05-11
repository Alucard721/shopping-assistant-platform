package com.niit.crawler.controller;


import com.niit.crawler.api.DTO.CrawlerRequestDTO;
import com.niit.crawler.facade.CategoryFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryFacade categoryFacade;

	/**
	 * @param crawlerRequestDTO
	 * @Description: 商品种类爬取
	 */
	@RequestMapping("/crawlerCategory")
	public void crawlerCategory(@RequestBody CrawlerRequestDTO crawlerRequestDTO) {
		categoryFacade.crawlerCategory(crawlerRequestDTO);
	}
}
