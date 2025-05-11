package com.niit.recommendation.serviceImpl;

import com.niit.recommendation.api.Indicators2Enum;
import com.niit.recommendation.entity.Indicators2;
import com.niit.recommendation.entity.Product;
import com.niit.recommendation.repository.Indicators1Repository;
import com.niit.recommendation.repository.Indicators2Repository;
import com.niit.recommendation.repository.ProductRepository;
import com.niit.recommendation.service.RecommendationService;
import com.niit.recommendation.utils.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	private static Logger logger = LoggerFactory.getLogger(RecommendationServiceImpl.class);

	@Autowired
	Indicators1Repository indicators1Repository;

	@Autowired
	Indicators2Repository indicators2Repository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public void evaluate() {
	  List<Product> products = this.findAllProducts();
      List<Product> productsBatch = new ArrayList<Product>();
		for (Product product : products) {
			//商品评分
			Integer sellCount;
			Integer reviewCount;

			try {
				sellCount = Integer.parseInt(ValidateUtils.validateCount(product.getSellCount()));
			} catch (NumberFormatException e) {
				sellCount = 100;
			}
			try {
				reviewCount = Integer.parseInt(ValidateUtils.validateCount(product.getReviewCount()));
			} catch (NumberFormatException e) {
				reviewCount = 100;
			}
			Double shopdsrMs = product.getShopdsrMs();
			Double shopdsrFw = product.getShopdsrFw();
			Double shopdsrWl = product.getShopdsrWl();
			//销量评分
			if (this.between(sellCount, 95000, 100000000)) {
				sellCount = 10;
			} else if (this.between(sellCount, 80000, 95000)) {
				sellCount = 9;
			} else if (this.between(sellCount, 50000, 80000)) {
				sellCount = 8;
			} else if (this.between(sellCount, 30000, 50000)) {
				sellCount = 7;
			} else if (this.between(sellCount, 10000, 30000)) {
				sellCount = 6;
			} else if (this.between(sellCount, 1, 10000)) {
				sellCount = 3;
			} else if (this.between(sellCount, 0, 0)) {
				sellCount = 0;
			}

			//评论量评分
			if (this.between(reviewCount, 10000, 100000000)) {
				reviewCount = 10;
			} else if (this.between(reviewCount, 5000, 10000)) {
				reviewCount = 9;
			} else if (this.between(reviewCount, 1000, 5000)) {
				reviewCount = 8;
			} else if (this.between(reviewCount, 100, 1000)) {
				reviewCount = 6;
			} else if (this.between(reviewCount, 10, 100)) {
				reviewCount = 4;
			} else if (this.between(reviewCount, 1, 10)) {
				reviewCount = 3;
			} else if (this.between(reviewCount, 0, 0)) {
				reviewCount = 0;
			}


			List<Indicators2> insdicators2s = indicators2Repository.findAll();
			double score = 0.0;
			for (Indicators2 indicators2 : insdicators2s) {
				if (Indicators2Enum.xl.getText().equals(indicators2.getIndicators2Name())) {
					score += sellCount * indicators2.getIndicators2Weight();
				}
				if (Indicators2Enum.pll.getText().equals(indicators2.getIndicators2Name())) {
					score += reviewCount * indicators2.getIndicators2Weight();
				}
			}
			boolean five = true;
			for (Indicators2 indicators2 : insdicators2s) {
				if (Indicators2Enum.mspf.getText().equals(indicators2.getIndicators2Name())) {
					score += (five ? 2 : 1) * shopdsrMs * indicators2.getIndicators2Weight();
				}
				if (Indicators2Enum.fwpf.getText().equals(indicators2.getIndicators2Name())) {
					score += (five ? 2 : 1) * shopdsrFw * indicators2.getIndicators2Weight();
				}
				if (Indicators2Enum.wlpf.getText().equals(indicators2.getIndicators2Name())) {
					score += (five ? 2 : 1) * shopdsrWl * indicators2.getIndicators2Weight();
				}
			}

			score = Math.round(score * 100.0) / 100.0;

			if(score == 9.24)
			{
				Random r = new Random();
				int num1 = 1 + r.nextInt(9);
				double num2 = num1 / 100;
				score -= num2;
			}

			// 推荐理由
			String recommendReason = "";
			try {
				if (20000 < Integer.parseInt(product.getSellCount())) {
					recommendReason += "销量好 ";
				}
			} catch (NumberFormatException e) {

			}

			if ("1".equals(product.getSpareField2()) || StringUtils.isEmpty(product.getSpareField2())) {
				if (4.8 <= product.getShopdsrMs()) {
					recommendReason += "店铺描述评分高 ";
				}
				if (4.8 <= product.getShopdsrFw()) {
					recommendReason += "店铺服务评分高 ";
				}
				if (4.8 <= product.getShopdsrWl()) {
					recommendReason += "店铺物流评分高 ";
				}
			} else if ("2".equals(product.getSpareField2())) {
				if (4.8 <= product.getShopdsrMs()) {
					recommendReason += "店铺描述评分高 ";
				}
				if (4.8 <= product.getShopdsrFw()) {
					recommendReason += "店铺服务评分高 ";
				}
				if (4.8 <= product.getShopdsrWl()) {
					recommendReason += "店铺物流评分高 ";
				}
			}
			logger.info("商品评分:{}商品描述:{}", score, recommendReason);
			product.setProductScore(score);
			product.setRecommendedReason(recommendReason);
			productsBatch.add(product);
			//分批处理
			if(productsBatch.size()==1000){
				productRepository.saveAll(productsBatch);
				productsBatch.clear();
			}
		}

		if (!productsBatch.isEmpty()) {
			productRepository.saveAll(productsBatch);
			productsBatch.clear();
		}
	}

	@Transactional
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	@Transactional
//	更新销量和评论量
	public void additional() {
		List<Product> products = this.findAllProducts();
		List<Product> productsBatch = new ArrayList<Product>();
		for (Product product : products) {
			product.setSellCount(ValidateUtils.validateCount(product.getSellCount()));
			product.setReviewCount(ValidateUtils.validateCount(product.getReviewCount()));
			if (!"100".equals(product.getSellCount()))
				logger.info("{}修改后为{}", product.getSellCount(), ValidateUtils.validateCount(product.getSellCount()));
		}
		productRepository.saveAll(products);

	}


//	判断数字是不是在一个区间
	private boolean between(Integer count, Integer min, Integer max) {
		if (count >= min && count <= max) {
			return true;
		} else {
			return false;
		}
	}

}
