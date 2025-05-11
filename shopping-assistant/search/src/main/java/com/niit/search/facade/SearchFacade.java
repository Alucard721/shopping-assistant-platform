package com.niit.search.facade;

import com.niit.search.api.DTO.SearchRequestDTO;
import com.niit.search.api.DTO.SearchResponseDTO;
import com.niit.search.service.SearchService;
import com.niit.search.serviceImpl.IndexServiceImpl;
import com.niit.search.serviceImpl.SearchServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SearchFacade {

	private static Logger logger = LoggerFactory.getLogger(SearchFacade.class);

	@Autowired
	private IndexServiceImpl indexService;

	@Autowired
	private SearchService searchService;

	@Scheduled(cron = "0 0 0/5 * * ?")
	public void indexAll(){
		logger.info("开始建立全量索引");
		indexService.indexAll();
	}

	public SearchResponseDTO search(SearchRequestDTO searchRequestDTO){
		return searchService.search(searchRequestDTO);
	}

	public SearchResponseDTO searchByUpperPrice(SearchRequestDTO searchRequestDTO){
		return searchService.searchByUpperPrice(searchRequestDTO);
	}

	public SearchResponseDTO searchByLowerPrice(SearchRequestDTO searchRequestDTO){
		return searchService.searchByLowerPrice(searchRequestDTO);
	}

	public SearchResponseDTO searchBySellCount(SearchRequestDTO searchRequestDTO){
		return searchService.searchBySellCount(searchRequestDTO);
	}

}
