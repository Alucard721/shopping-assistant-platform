package com.niit.crawler.facade;

import com.niit.crawler.api.DTO.CrawlerRequestDTO;
import com.niit.crawler.api.VO.CategoryVO;
import com.niit.crawler.entity.Category;
import com.niit.crawler.service.CategoryService;
import com.niit.crawler.utils.WebDriverFactory;
import com.niit.crawler.api.VO.Category1ToCategory2VO;
import com.niit.crawler.api.VO.Category2NameUrlVO;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryFacade {

	private static Logger logger = LoggerFactory.getLogger(CategoryFacade.class);

	@Autowired
	private CategoryService categoryService;

	/**
	 * @param crawlerRequestDTO
	 * @Description: 爬取天猫商品种类https://m.tmall.com/mblist/category/index.html
	 */
	public void crawlerCategory(CrawlerRequestDTO crawlerRequestDTO) {
		// 获取浏览器驱动
		WebDriver driver = WebDriverFactory.getInstance();
		driver.get(crawlerRequestDTO.getUrl());
		this.wait(driver);
		CategoryVO categoryVO = this.getAllCategory1(driver);

		logger.info("success,商品种类爬取完毕哦！！！");

	}

	/**
	 * @param driver
	 * @Description: 等待界面加载
	 */
	private void wait(WebDriver driver) {
		// 等待获取结果页
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.className("copyright")));
		logger.info("开始！");
	}

	/**
	 * @param driver
	 * @return CategoryVO
	 * @Description: 获取所有商品种类
	 */
	private CategoryVO getAllCategory1(WebDriver driver) {
		List<WebElement> elements = null;
		List<Category> categories = new ArrayList<>();
		try {
			elements = driver.findElements(By.className("cat-1-li"));
		} catch (Exception e) {
			logger.info("一级类目未找到");
		}

		//存放一级类目数组
		List<Category1ToCategory2VO> category1ToCategory2VOList = new ArrayList<>();

		// 一级类目循环
		for (WebElement element : elements) {
			Category1ToCategory2VO category1ToCategory2VO = new Category1ToCategory2VO();
			String categoryName1 = element.findElement(By.tagName("h1")).findElement(By.tagName("a")).getAttribute("title");
			category1ToCategory2VO.setCategory1Name(categoryName1);

			List<WebElement> elementsOfCategoryName2;
			try {
				elementsOfCategoryName2 = element.findElement(By.className("catlist-2")).findElements(By.tagName("li"));
			} catch (Exception e) {
				continue;
			}

			//存放二级类目名和url
			List<Category2NameUrlVO> category2NameUrlVOList = new ArrayList<>();

			for (WebElement element1 : elementsOfCategoryName2) {
				String category2Name = null;
				String category2Url = null;
				try {
					category2Name = element1.findElement(By.tagName("h2")).findElement(By.tagName("a")).getAttribute("title");
					category2Url = element1.findElement(By.tagName("h2")).findElement(By.tagName("a")).getAttribute("href");
				} catch (Exception e) {
					category2Name = element1.findElement(By.tagName("a")).getAttribute("title");
					category2Url = element1.findElement(By.tagName("a")).getAttribute("href");
				}

				Category2NameUrlVO category2NameUrlVO = new Category2NameUrlVO(category2Name, category2Url);
				category2NameUrlVOList.add(category2NameUrlVO);

				// 检查当前二级分类下是否有三级分类
				List<WebElement> elementsOfCategoryName3;

				try {
					elementsOfCategoryName3 = element1.findElements(By.tagName("li"));
				} catch (Exception e) {
					continue;
				}

				for (WebElement elementOfCategoryName3 : elementsOfCategoryName3) {
					String categoryName3 = elementOfCategoryName3.findElement(By.tagName("a")).getAttribute("title");
					logger.info("三类标签get {}",categoryName3);
					Category category = new Category("", categoryName1, category2Name, categoryName3);
					categories.add(category);
				}
			}

			logger.info("{}搜集完毕", categoryName1);
			category1ToCategory2VO.setCategory2NameUrlVOS(category2NameUrlVOList);
			category1ToCategory2VOList.add(category1ToCategory2VO);
		}

		categoryService.saveCategory(categories);
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategory2VOList(category1ToCategory2VOList);
		logger.info("category1ToCategory2VOList的大小{}", category1ToCategory2VOList.size());
		return categoryVO;
	}

}
