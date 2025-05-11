package com.niit.search.service;

import com.niit.search.api.DTO.SearchRequestDTO;
import com.niit.search.api.DTO.SearchResponseDTO;

public interface SearchService {

	 SearchResponseDTO search(SearchRequestDTO searchRequestDTO);

	 SearchResponseDTO searchByUpperPrice(SearchRequestDTO searchRequestDTO);

	 SearchResponseDTO searchByLowerPrice(SearchRequestDTO searchRequestDTO);

	 SearchResponseDTO searchBySellCount(SearchRequestDTO searchRequestDTO);
}
