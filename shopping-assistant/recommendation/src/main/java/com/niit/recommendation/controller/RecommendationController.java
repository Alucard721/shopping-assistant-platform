package com.niit.recommendation.controller;

import com.niit.recommendation.facade.RecommendationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/recommend")
public class RecommendationController {

	private static Logger logger = LoggerFactory.getLogger(RecommendationController.class);

	@Autowired
	RecommendationFacade recommendationFacade;

	/**
	 * @Description: 处理补充商品数据
	 */
	@RequestMapping("/additional")
	public void additional(){
 		recommendationFacade.additional();
	}

  /**
   * @Description: 商品评分
   */
  @RequestMapping("/evaluate")
  public void evaluate(){
	  recommendationFacade.evaluate();
  }

}
