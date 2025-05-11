package com.niit.crawler.facade;

import com.niit.crawler.api.DTO.CrawlerRequestDTO;
import com.niit.crawler.entity.Product;
import com.niit.crawler.service.ProductJDService;
import com.niit.crawler.utils.WebDriverFactory;
import com.niit.crawler.service.CategoryService;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductJDFacade {

	private static Logger logger = LoggerFactory.getLogger(ProductJDFacade.class);

	@Autowired
	private ProductJDService productJDService;

	@Autowired
	private CategoryService categoryService;

	private int loginCount = 0;


	/**
	 * @param crawlerRequestDTO
	 * @return void
	 * @Description: 爬取主流程入口
	 */
	public void startCrawlerJD(CrawlerRequestDTO crawlerRequestDTO) throws IOException {
		// 获取浏览器驱动
		WebDriver driver = WebDriverFactory.getInstance();

		// 查询关键词
		List<String> keys = categoryService.findAllCategory();
		logger.info("查找标签中...");
//		List<String> keys = new ArrayList<String>();
//		keys.add("笔记本");
		while (true) {

//			控制循环的流程
			l:
			for (String key : keys) {

				try {
					// 提交关键词至搜索结果页
					this.basicOperation(crawlerRequestDTO, driver, key);
					// 爬取搜索结果信息
					this.keyCrawler(driver, key);
					while (true) {
						// 等待回到原来搜索结果页
						new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.className("gl-item")));
						int curPage = productJDService.getCurPageNum(driver);
						int pageTotal = productJDService.getPageTotal(driver);
						// 非末页，则点击下一页
						if (pageTotal >= curPage) {
							productJDService.toNextPage(driver);
							new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.className("gl-item")));
							if (curPage == productJDService.getCurPageNum(driver)) {
								continue l;
							}
							new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.className("gl-item")));
							this.keyCrawler(driver, key);
						} else {
							/*driver.quit();*/
							continue l;
						}
					}
				} catch (Exception e) {
					//不做处理
					e.printStackTrace();
//					driver.quit();
				}
			}
		}
	}

	/**
	 * @param driver
	 * @param key
	 * @return void
	 * @Description: 爬取某个关键词的搜索结果
	 */
	public void keyCrawler(WebDriver driver, String key) {
		new WebDriverWait(driver, 20, 3000).until(ExpectedConditions.presenceOfElementLocated(By.className("gl-item")));
		// 获取搜索结果页当页数据
		List<WebElement> searchResults = ((EdgeDriver) driver).findElementsByClassName("gl-item");
		// 获取当前url，便于当页数据爬完后返回
		String curUrl = driver.getCurrentUrl();
		List<String> urls = new ArrayList<String>();
		List<String> prices = new ArrayList<String>(); // 新增一个用于存储价格的列表
		for (WebElement element : searchResults) {
			urls.add(element.findElement(By.className("p-img")).findElement(By.tagName("a")).getAttribute("href"));
			// 爬取价格信息并存储
			prices.add(element.findElement(By.className("p-price")).findElement(By.tagName("i")).getText());
		}
		// 对当页商品进行爬取
		for (int i = 0; i < urls.size(); i++) {
			String url = urls.get(i);
			String price = prices.get(i); // 获取对应商品的价格信息
			Product product = productJDService.crawlingOne(driver, url);
			product.setPrice(price); // 设置商品的价格信息
			product.setSpareField1(Double.parseDouble(price));
//			productTemp目前没什么作用
			Product productTemp = productJDService.findProductByProductId(product.getProductId());
			productJDService.saveProductInfo(product, productTemp, key);
		}
		// 回到搜索结果页
		driver.get(curUrl);
	}



	/**
	 * @param crawlerRequestDTO
	 * @return WebDriver
	 * @Description: 获取关键词查询，并返回driver
	 */
	private WebDriver basicOperation(CrawlerRequestDTO crawlerRequestDTO, WebDriver driver, String key) {
		// 隐性等待
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(crawlerRequestDTO.getUrl());

		// 判断是否登录
		if (loginCount ==0 && driver.findElement(By.className("link-login")) != null) {
			driver.findElement(By.className("link-login")).click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("formlogin")));
			try {
				Thread.sleep(20);
				driver.findElement(By.id("loginname")).sendKeys("13208979162");
				driver.findElement(By.id("nloginpwd")).sendKeys("qq717415309");
				Thread.sleep(20);
				driver.findElement(By.id("loginsubmit")).click();
				new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.className("nickname")));
				Thread.sleep(20);
				loginCount++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 输入关键词
		WebElement inputelement = driver.findElement(By.id("key"));
		inputelement.sendKeys(key);
		// 表单提交
		WebElement searchTop = driver.findElement(By.className("button"));
		searchTop.click();
		return driver;
	}
}
