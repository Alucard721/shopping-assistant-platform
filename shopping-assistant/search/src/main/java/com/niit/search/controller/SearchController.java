package com.niit.search.controller;

import com.niit.search.api.DTO.SearchRequestDTO;
import com.niit.search.api.DTO.SearchResponseDTO;
import com.niit.search.entity.Product;
import com.niit.search.facade.SearchFacade;
import com.niit.search.service.SearchService;
import com.niit.search.serviceImpl.SearchServiceImpl;
import com.niit.search.utils.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/search")

public class SearchController {

	private static Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchFacade searchFacade;


	/**
	 * @return void
	 * @Description: 建立全量索引任务
	 */
   @RequestMapping("/indexAll")
   public ResponseEntity<String> indexAll() {
	   searchFacade.indexAll();
	   return ResponseEntity.ok("索引建立完成");
   }

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public SearchResponseDTO search(@RequestBody SearchRequestDTO searchRequestDTO) {
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		try {
			searchResponseDTO = searchFacade.search(searchRequestDTO);
		} catch (NullPointerException e) {
			logger.error("查询出错");
		}

		if (searchResponseDTO.getTotal() == 0) {
			return searchResponseDTO;
		}
		for (Product product : searchResponseDTO.getProducts()) {
			product.setProductImg(product.getProductImg().split(",")[0]);
			product.setReviewCount(ValidateUtils.validateCount(product.getReviewCount()));
		}
		logger.info("商品数量{}", searchResponseDTO.getProducts().size());
		return searchResponseDTO;
	}

	//	价格从高到低排序
	@RequestMapping(value = "/search2", method = RequestMethod.POST)
	@ResponseBody
	public SearchResponseDTO search2(@RequestBody SearchRequestDTO searchRequestDTO) {
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		try {
			searchResponseDTO = searchFacade.searchByUpperPrice(searchRequestDTO);
		} catch (NullPointerException e) {
			logger.error("查询出错");
		}

		if (searchResponseDTO.getTotal() == 0) {
			return searchResponseDTO;
		}
		for (Product product : searchResponseDTO.getProducts()) {
			product.setProductImg(product.getProductImg().split(",")[0]);
			product.setReviewCount(ValidateUtils.validateCount(product.getReviewCount()));
		}
		logger.info("商品数量{}", searchResponseDTO.getProducts().size());
		return searchResponseDTO;
	}

	//价格从低到高排序
	@RequestMapping(value = "/search3", method = RequestMethod.POST)
	@ResponseBody
	public SearchResponseDTO search3(@RequestBody SearchRequestDTO searchRequestDTO) {
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		try {
			searchResponseDTO = searchFacade.searchByLowerPrice(searchRequestDTO);
		} catch (NullPointerException e) {
			logger.error("查询出错");
		}

		if (searchResponseDTO.getTotal() == 0) {
			return searchResponseDTO;
		}
		for (Product product : searchResponseDTO.getProducts()) {
			product.setProductImg(product.getProductImg().split(",")[0]);
			product.setReviewCount(ValidateUtils.validateCount(product.getReviewCount()));
		}
		logger.info("商品数量{}", searchResponseDTO.getProducts().size());
		return searchResponseDTO;
	}

	//	按销量排序
	@RequestMapping(value = "/search4", method = RequestMethod.POST)
	@ResponseBody
	public SearchResponseDTO search4(@RequestBody SearchRequestDTO searchRequestDTO) {
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		try {
			searchResponseDTO = searchFacade.searchBySellCount(searchRequestDTO);
		} catch (NullPointerException e) {
			logger.error("查询出错");
		}

		if (searchResponseDTO.getTotal() == 0) {
			return searchResponseDTO;
		}
		for (Product product : searchResponseDTO.getProducts()) {
			product.setProductImg(product.getProductImg().split(",")[0]);
			product.setReviewCount(ValidateUtils.validateCount(product.getReviewCount()));
		}
		logger.info("商品数量{}", searchResponseDTO.getProducts().size());
		return searchResponseDTO;
	}

//	价格从高到低排序
//	@RequestMapping(value = "/search3", method = RequestMethod.GET)
//	@ResponseBody
//	public SearchResponseDTO search3() {
//
//		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
//		try {
//			SearchRequestDTO searchRequestDTO = new SearchRequestDTO("复读机","1","20");
//			searchRequestDTO.setMallFilter("2");
//			searchResponseDTO = searchFacade.searchByUpperPrice(searchRequestDTO);
//		} catch (NullPointerException e) {
//			logger.error("查询出错");
//		}
//
//		if (searchResponseDTO.getTotal() == 0) {
//			return searchResponseDTO;
//		}
//		for (Product product : searchResponseDTO.getProducts()) {
//			product.setProductImg(product.getProductImg().split(",")[0]);
//			product.setReviewCount(ValidateUtils.validateCount(product.getReviewCount()));
//		}
//		logger.info("商品数量{}", searchResponseDTO.getProducts().size());
//
//		return searchResponseDTO;
//	}
//
}

