package com.niit.crawler.service;

import com.niit.crawler.entity.Product;
import com.niit.crawler.repository.ProductRepository;
import com.niit.crawler.utils.ValidationUtil;
import org.apache.http.HttpRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.http.HttpClient;

import javax.validation.ValidationException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ProductJDService {

	private static Logger logger = LoggerFactory.getLogger(ProductJDService.class);

	@Autowired
	private ProductRepository productRepository;

	/**
	 * @param product
	 * @return void
	 * @Description: 保存一个商品信息
	 */
	@Transactional
	public void saveProductInfo(Product product, Product productTemp,String key) {
		try{
			ValidationUtil.validate(product);
		}catch (ValidationException e){
			product.setSpareField3(e.getMessage());
			productTemp.setSpareField3(e.getMessage());
		}

		if (productTemp != null) {
			productTemp.setProductId(product.getProductId());
			productTemp.setProductName(product.getProductName());
			productTemp.setCategoryName(product.getCategoryName());
			productTemp.setSellCount(product.getSellCount());
			productTemp.setReviewCount(product.getReviewCount());
			productTemp.setStock(product.getStock());
			productTemp.setShopId(product.getShopId());
			productTemp.setShopName(product.getShopName());
			productTemp.setShopdsrMs(product.getShopdsrMs());
			productTemp.setShopdsrFw(product.getShopdsrFw());
			productTemp.setShopdsrWl(product.getShopdsrWl());
			productTemp.setProductUrl(product.getProductUrl());
			productTemp.setProductDetail(product.getProductDetail());
			productTemp.setProductScore(product.getProductScore());
			productTemp.setRecommendedReason(product.getRecommendedReason());
			productTemp.setPrice(product.getPrice());
			productTemp.setProductImg(product.getProductImg());
			productTemp.setUpdateTime(product.getUpdateTime());
			productTemp.setSpareField1(product.getSpareField1());
			productTemp.setCategoryName(key);
			productTemp.setSpareField2("2");
			productRepository.saveAndFlush(productTemp);
			//更新索引
		} else {
			product.setCategoryName(key);
			product.setSpareField2("2");
			productRepository.saveAndFlush(product);
		}

	}

	/**
	 * @param productId
	 * @return Product
	 * @Description: 根据商品id获取商品信息
	 */
	@Transactional
	public Product findProductByProductId(String productId) {
		List<Product> products = productRepository.findProductsByProductId(productId);
		if (products != null && products.size() >= 1) {
			return products.get(0);
		}
		return null;
	}

	/**
	 * @param driver
	 * @param url
	 * @return Product
	 * @Description: 根据商品详情页的url爬取商品信息
	 */
	public Product crawlingOne(WebDriver driver, String url) {
		Product product = null;
		product = new Product();
		String productId = null;
		driver.get(url);
//		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-price .price")));
//		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("p-price")));
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("sku-name")));
		try {
			productId = driver.findElement(By.className("left-btns")).findElement(By.tagName("a")).getAttribute("data-id");
			product.setProductId(productId == null ? "" : productId);
		} catch (Exception e) {
			product.setProductId("");
		}
		try {
			String productName = driver.findElement(By.className("sku-name")).getText();
			product.setProductName(productName == null ? "" : productName);
			product.setProductDetail("这是一个" + productName);
		} catch (Exception e) {
			product.setProductName("");
		}

		//价格爬取
//		try {
//
//			String curPrice = driver.findElements(By.className("p-price")).get(0).findElement(By.className("price")).getText();
//			product.setPrice(curPrice == null ? "," : curPrice + ","); //new PricePO(String.valueOf(new Date()), curPrice)
//			product.setSpareField1(Double.parseDouble(curPrice));
//
//		} catch (Exception e) {
//			Random r = new Random();
//			int num = r.nextInt(600);
//			String curPrice = num + ".00,";
//			product.setPrice(curPrice);
//			product.setSpareField1(Double.parseDouble(curPrice));
//		}


		//jd无销量显示
		try {
			Random r = new Random();
			Integer sellCount = r.nextInt(100000);
			product.setSellCount(sellCount);
		} catch (Exception e) {
			product.setSellCount(1000);
		}
		try {
			String reviewCount = driver.findElement(By.id("comment-count")).findElement(By.tagName("a")).getText();
			product.setReviewCount(reviewCount == null ? "" : reviewCount);
		} catch (Exception e) {
			product.setReviewCount("100");
		}
		try {
			int stock = 1; //有货
			product.setStock(stock);
		} catch (Exception e) {
			product.setStock(0);
		}
		try {
			String shopId = driver.findElements(By.className("btns")).get(0).findElements(By.tagName("a")).get(1).getAttribute("data-vid");
			product.setShopId(shopId == null ? "" : shopId);
		} catch (Exception e) {
			product.setShopId("");
		}
		try {
			//店铺名
			String shopName = driver.findElement(By.className("popbox-inner")).findElement(By.className("mt")).findElement(By.tagName("a")).getText();
			product.setShopName(shopName == null ? "" : shopName);
		} catch (Exception e) {
			product.setShopName("");
		}
		try {
			String productImg = driver.findElement(By.id("spec-img")).getAttribute("src");
			product.setProductImg(productImg == null ? "," : productImg + ","); //new ImgPo("", driver.findElement(By.id("J_ImgBooth")).getAttribute("src"))
		} catch (Exception e) {
			product.setProductImg("");
		}
		try{
			//京东自营的店铺不显示店铺分数
			driver.findElement(By.className("u-jd"));
			product.setShopdsrMs(4.8);
			product.setShopdsrFw(4.8);
			product.setShopdsrWl(4.8);
		}catch (Exception e1){
		try {
			double shopdsrMs = Double.parseDouble(driver.findElement(By.className("score-parts")).findElements(By.className("score-detail")).get(0).findElement(By.tagName("em")).getAttribute("title").split("分")[0]);
			product.setShopdsrMs(shopdsrMs);
		} catch (Exception e) {
			product.setShopdsrMs(0.0);
		}
		try {
			double shopdsrFw = Double.parseDouble(driver.findElement(By.className("score-parts")).findElements(By.className("score-detail")).get(2).findElement(By.tagName("em")).getAttribute("title").split("分")[0]);
			product.setShopdsrFw(shopdsrFw);
		} catch (Exception e) {
			product.setShopdsrFw(0.0);
		}
		try {
			double shopdsrWl = Double.parseDouble(driver.findElement(By.className("score-parts")).findElements(By.className("score-detail")).get(1).findElement(By.tagName("em")).getAttribute("title").split("分")[0]);
			product.setShopdsrWl(shopdsrWl);
		} catch (Exception e) {
			product.setShopdsrWl(0.0);
		}
			}

		product.setProductUrl(url);
		product.setUpdateTime(String.valueOf(new Date()) + ",");
		logger.info(product.toString());
		return product;
	}

	/**
	 * @param driver
	 * @return curPageNum
	 * @Description: 搜索结果页获取当前页码
	 */
	public int getCurPageNum(WebDriver driver) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("p-wrap")));
		String curPageNum = driver.findElement(By.className("p-num")).findElement(By.className("curr")).getText();
		return Integer.parseInt(curPageNum);
	}

	/**
	 * @param driver
	 * @return int
	 * @Description: 搜索结果页获取总页数
	 */
	public int getPageTotal(WebDriver driver) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("p-skip")));
		String pageTotal = driver.findElement(By.className("p-skip")).findElement(By.tagName("b")).getText();
		return Integer.parseInt(pageTotal);
	}

	/**
	 * @param driver
	 * @return void
	 * @Description: 点击下一页
	 */
	public void toNextPage(WebDriver driver) {
		new WebDriverWait(driver, 10, 3000).until(ExpectedConditions.presenceOfElementLocated(By.className("pn-next")));
		driver.findElement(By.className("pn-next")).click();
	}
}
