package com.niit.recommendation.facade;

import com.niit.recommendation.service.RecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationFacade {

	private static Logger logger = LoggerFactory.getLogger(RecommendationFacade.class);

	@Autowired
	RecommendationService recommendationService;

	public void additional(){
		recommendationService.additional();
	}

	public void evaluate(){
		recommendationService.evaluate();
	}
}
