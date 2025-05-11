package com.niit.crawler.controller;

import com.niit.crawler.api.DTO.CrawlerRequestDTO;
import com.niit.crawler.facade.ProductJDFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/crawler")
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductJDFacade productJDFacade;

	/**
	 * @param crawlerRequestDTO
	 * @Description: 爬取主流程入口
	 */
	@RequestMapping("/startCrawlerJD")
	public void startCrawlerJD(@RequestBody CrawlerRequestDTO crawlerRequestDTO) throws IOException {
		productJDFacade.startCrawlerJD(crawlerRequestDTO);
	}

}
