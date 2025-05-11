package com.niit.search.serviceImpl;

import com.niit.search.api.DTO.SearchRequestDTO;
import com.niit.search.api.DTO.SearchResponseDTO;
import com.niit.search.entity.Product;
import com.niit.search.repository.ProductRepository;
import com.niit.search.service.SearchService;
import com.niit.search.utils.ValidateUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SearchServiceImpl implements SearchService {

	private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

	private static String DIR_PATH = "D:\\code\\Graduationproject\\LuceneDocument";

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	@ResponseBody
	public SearchResponseDTO search(SearchRequestDTO searchRequestDTO) {
		logger.info("开始查询数据----------");
		CJKAnalyzer ikAnalyzer = new CJKAnalyzer();
		IndexReader indexReader = null;
		// 存放搜索结果
		List<Product> products = new ArrayList<Product>();
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		try {
			Directory directory = FSDirectory.open(Paths.get(DIR_PATH));
			//传入读取文档所在的路径初始化Reader
			indexReader = DirectoryReader.open(directory);
			//IndexSearcher为Lucene搜索的核心组件
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			// QueryParser用于解析传入的搜索关键字
			QueryParser parser = new QueryParser("productName", ikAnalyzer);
			Query query = parser.parse(searchRequestDTO.getKey());

			//此处加入的是搜索结果的高亮部分
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color=red>", "</font></b>"); //如果不指定参数的话，默认是加粗，即<b><b/>
			QueryScorer scorer = new QueryScorer(query);//计算得分，会初始化一个查询结果最高的得分
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer); //根据这个得分计算出一个片段
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
			highlighter.setTextFragmenter(fragmenter); //设置一下要显示的片段

			//执行搜索，返回最多50000个匹配的文档,保存在hits中
			TopDocs hits = indexSearcher.search(query, 50000);

			//如果搜索结果为空
			if (hits.scoreDocs.length == 0) {
				searchResponseDTO.setProducts(null);
				searchResponseDTO.setTotal(0);
				searchResponseDTO.setTotalPage(0);
				logger.info("---查询成功，查询结果数量{}--", 0);
				return searchResponseDTO;
			}
			//转list
			List<ScoreDoc> scoreDocs = new ArrayList<ScoreDoc>();

			//取出每条查询结果放在scoreDocs里
			for (int i = 0; i < hits.scoreDocs.length; i++) {
				scoreDocs.add(hits.scoreDocs[i]);
			}

			//对结果高亮显示
			for (int i = 0; i < scoreDocs.size(); i++) {
				Document document = indexSearcher.doc(scoreDocs.get(i).doc);
				Product product = this.setProduct(document, ikAnalyzer, highlighter);
				products.add(product);
			}

			//商城筛选
			products = this.mallFilter(products, searchRequestDTO.getMallFilter());

			Integer total = products.size();

			//通过排序条件进行排序
			this.sortByScore(products);

			logger.info("---查询成功，查询结果数量{}--", products.size());
			int pageNum = Integer.parseInt(searchRequestDTO.getPageNum());
			int pageSize = Integer.parseInt(searchRequestDTO.getPageSize());
			Integer totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

			//搜索结果大于1页 根据pageSize和pageNum进行分页
			int fromIndex = (pageNum - 1) * pageSize; // pageNum从1开始，所以减去1
			int toIndex = Math.min(pageNum * pageSize, products.size());

			if (fromIndex < products.size()) { // 确保起始索引小于列表大小
				products = products.subList(fromIndex, toIndex);
			}

			searchResponseDTO.setProducts(products);
			searchResponseDTO.setTotal(total);
			searchResponseDTO.setTotalPage(totalPage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				indexReader.close();
			} catch (Exception e1) {
				logger.info("关闭writer错误");
				e1.printStackTrace();
			}
		}
		return searchResponseDTO;

	}

	@Override
	@ResponseBody
	public SearchResponseDTO searchByUpperPrice(SearchRequestDTO searchRequestDTO) {
		logger.info("开始查询数据----------");
		CJKAnalyzer ikAnalyzer = new CJKAnalyzer();
		IndexReader indexReader = null;
		// 存放搜索结果
		List<Product> products = new ArrayList<Product>();
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		try {
			Directory directory = FSDirectory.open(Paths.get(DIR_PATH));
			indexReader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			QueryParser parser = new QueryParser("productName", ikAnalyzer);
			Query query = parser.parse(searchRequestDTO.getKey());

			//此处加入的是搜索结果的高亮部分
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color=red>", "</font></b>"); //如果不指定参数的话，默认是加粗，即<b><b/>
			QueryScorer scorer = new QueryScorer(query);//计算得分，会初始化一个查询结果最高的得分
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer); //根据这个得分计算出一个片段
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
			highlighter.setTextFragmenter(fragmenter); //设置一下要显示的片段

			TopDocs hits = indexSearcher.search(query, 50000);

			//如果搜索结果为空
			if (hits.scoreDocs.length == 0) {
				searchResponseDTO.setProducts(null);
				searchResponseDTO.setTotal(0);
				searchResponseDTO.setTotalPage(0);
				logger.info("---查询成功，查询结果数量{}--", 0);
				return searchResponseDTO;
			}
			//转list
			List<ScoreDoc> scoreDocs = new ArrayList<ScoreDoc>();

			for (int i = 0; i < hits.scoreDocs.length; i++) {
				scoreDocs.add(hits.scoreDocs[i]);
			}

			//对结果高亮显示
			for (int i = 0; i < scoreDocs.size(); i++) {
				Document document = indexSearcher.doc(scoreDocs.get(i).doc);
				Product product = this.setProduct(document, ikAnalyzer, highlighter);
				products.add(product);
			}

			//商城筛选
			products = this.mallFilter(products, searchRequestDTO.getMallFilter());

			Integer total = products.size();

			//通过排序条件进行排序
			this.sortByUpperPrice(products);
//			for (Product p : products){
//				System.out.println(p.getPrice());
//			}

			logger.info("---查询成功，查询结果数量{}--", products.size());
			int pageNum = Integer.parseInt(searchRequestDTO.getPageNum());
			int pageSize = Integer.parseInt(searchRequestDTO.getPageSize());
			Integer totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

			//搜索结果大于1页 根据pageSize和pageNum进行分页
			int fromIndex = (pageNum - 1) * pageSize; // pageNum从1开始，所以减去1
			int toIndex = Math.min(pageNum * pageSize, products.size());

			if (fromIndex < products.size()) { // 确保起始索引小于列表大小
				products = products.subList(fromIndex, toIndex);
			}
//			if (products.size() > pageSize) {
//				products = products.subList(pageNum * pageSize, pageNum * pageSize + pageSize);
//			}

			searchResponseDTO.setProducts(products);
			searchResponseDTO.setTotal(total);
			searchResponseDTO.setTotalPage(totalPage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				indexReader.close();
			} catch (Exception e1) {
				logger.info("关闭writer错误");
				e1.printStackTrace();
			}
		}
		return searchResponseDTO;

	}

	@Override
	@ResponseBody
	public SearchResponseDTO searchByLowerPrice(SearchRequestDTO searchRequestDTO) {
		logger.info("开始查询数据----------");
		CJKAnalyzer ikAnalyzer = new CJKAnalyzer();
		IndexReader indexReader = null;
		// 存放搜索结果
		List<Product> products = new ArrayList<Product>();
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		try {
			Directory directory = FSDirectory.open(Paths.get(DIR_PATH));
			indexReader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			QueryParser parser = new QueryParser("productName", ikAnalyzer);
			Query query = parser.parse(searchRequestDTO.getKey());

			//此处加入的是搜索结果的高亮部分
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color=red>", "</font></b>"); //如果不指定参数的话，默认是加粗，即<b><b/>
			QueryScorer scorer = new QueryScorer(query);//计算得分，会初始化一个查询结果最高的得分
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer); //根据这个得分计算出一个片段
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
			highlighter.setTextFragmenter(fragmenter); //设置一下要显示的片段

			TopDocs hits = indexSearcher.search(query, 50000);

			//如果搜索结果为空
			if (hits.scoreDocs.length == 0) {
				searchResponseDTO.setProducts(null);
				searchResponseDTO.setTotal(0);
				searchResponseDTO.setTotalPage(0);
				logger.info("---查询成功，查询结果数量{}--", 0);
				return searchResponseDTO;
			}
			//转list
			List<ScoreDoc> scoreDocs = new ArrayList<ScoreDoc>();

			for (int i = 0; i < hits.scoreDocs.length; i++) {
				scoreDocs.add(hits.scoreDocs[i]);
			}

			//对结果转换为products,并加入高亮显示
			for (int i = 0; i < scoreDocs.size(); i++) {
				Document document = indexSearcher.doc(scoreDocs.get(i).doc);
				Product product = this.setProduct(document, ikAnalyzer, highlighter);
				products.add(product);
			}

			//商城筛选
			products = this.mallFilter(products, searchRequestDTO.getMallFilter());

			Integer total = products.size();

			//通过排序条件进行排序
			this.sortByLowerPrice(products);

			logger.info("---查询成功，查询结果数量{}--", products.size());

			//对结果进行分页处理
			int pageNum = Integer.parseInt(searchRequestDTO.getPageNum());
			int pageSize = Integer.parseInt(searchRequestDTO.getPageSize());
			Integer totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

			//搜索结果大于1页 根据pageSize和pageNum进行分页
			int fromIndex = (pageNum - 1) * pageSize; // pageNum从1开始，所以减去1
			int toIndex = Math.min(pageNum * pageSize, products.size());

			if (fromIndex < products.size()) { // 确保起始索引小于列表大小
				products = products.subList(fromIndex, toIndex);
			} else {
				products = new ArrayList<>(); // 如果起始索引超出范围，则返回空列表
			}

//			if (products.size() > pageSize) {
//				products = products.subList(pageNum * pageSize, pageNum * pageSize + pageSize);
//			}

			//设置搜索结果:产品列表, 结果数, 总页数
			searchResponseDTO.setProducts(products);
			searchResponseDTO.setTotal(total);
			searchResponseDTO.setTotalPage(totalPage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				indexReader.close();
			} catch (Exception e1) {
				logger.info("关闭writer错误");
				e1.printStackTrace();
			}
		}
		return searchResponseDTO;

	}

	@Override
	@ResponseBody
	public SearchResponseDTO searchBySellCount(SearchRequestDTO searchRequestDTO) {
		logger.info("开始查询数据----------");
		CJKAnalyzer ikAnalyzer = new CJKAnalyzer();
		IndexReader indexReader = null;
		// 存放搜索结果
		List<Product> products = new ArrayList<Product>();
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		try {
			Directory directory = FSDirectory.open(Paths.get(DIR_PATH));
			indexReader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
			QueryParser parser = new QueryParser("productName", ikAnalyzer);
			Query query = parser.parse(searchRequestDTO.getKey());

			//此处加入的是搜索结果的高亮部分
			SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color=red>", "</font></b>"); //如果不指定参数的话，默认是加粗，即<b><b/>
			QueryScorer scorer = new QueryScorer(query);//计算得分，会初始化一个查询结果最高的得分
			Fragmenter fragmenter = new SimpleSpanFragmenter(scorer); //根据这个得分计算出一个片段
			Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
			highlighter.setTextFragmenter(fragmenter); //设置一下要显示的片段

			TopDocs hits = indexSearcher.search(query, 50000);

			//如果搜索结果为空
			if (hits.scoreDocs.length == 0) {
				searchResponseDTO.setProducts(null);
				searchResponseDTO.setTotal(0);
				searchResponseDTO.setTotalPage(0);
				logger.info("---查询成功，查询结果数量{}--", 0);
				return searchResponseDTO;
			}
			//转list
			List<ScoreDoc> scoreDocs = new ArrayList<ScoreDoc>();

			for (int i = 0; i < hits.scoreDocs.length; i++) {
				scoreDocs.add(hits.scoreDocs[i]);
			}

			//对结果高亮显示
			for (int i = 0; i < scoreDocs.size(); i++) {
				Document document = indexSearcher.doc(scoreDocs.get(i).doc);
				Product product = this.setProduct(document, ikAnalyzer, highlighter);
				products.add(product);
			}

			//商城筛选
			products = this.mallFilter(products, searchRequestDTO.getMallFilter());

			Integer total = products.size();

			//通过排序条件进行排序
			this.sortBySellCount(products);

			logger.info("---查询成功，查询结果数量{}--", products.size());
			int pageNum = Integer.parseInt(searchRequestDTO.getPageNum());
			int pageSize = Integer.parseInt(searchRequestDTO.getPageSize());
			Integer totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

			//搜索结果大于1页 根据pageSize和pageNum进行分页
			int fromIndex = (pageNum - 1) * pageSize; // pageNum从1开始，所以减去1
			int toIndex = Math.min(pageNum * pageSize, products.size());

			if (fromIndex < products.size()) { // 确保起始索引小于列表大小
				products = products.subList(fromIndex, toIndex);
			} else {
				products = new ArrayList<>(); // 如果起始索引超出范围，则返回空列表
			}

//			if (products.size() > pageSize) {
//				products = products.subList(pageNum * pageSize, pageNum * pageSize + pageSize);
//			}

			searchResponseDTO.setProducts(products);
			searchResponseDTO.setTotal(total);
			searchResponseDTO.setTotalPage(totalPage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				indexReader.close();
			} catch (Exception e1) {
				logger.info("关闭writer错误");
				e1.printStackTrace();
			}
		}
		return searchResponseDTO;

	}

	/**
	 * @param products mallFilter
	 * @return TopDocs
	 * @Description: 根据商城对商品进行过滤
	 */
	private List<Product> mallFilter(List<Product> products, String mallFilter) {
		if (null == mallFilter || "".equals(mallFilter)) {
			return products;
		}
		String[] mallFilterArray = mallFilter.split(",");
		List<String> mallFilterList = Arrays.asList(mallFilterArray);
		//点击了全部 或无筛选条件
		if (mallFilterList.contains("all")) {
			return products;
		}
		Iterator iterator = products.iterator();
		while (iterator.hasNext()) {
			Product product = (Product) iterator.next();
			if (!mallFilterList.contains(product.getSpareField2())) {
				iterator.remove();
			}
		}

		return products;
	}

	/**
	 * @param products
	 * @Description: 根据评分进行排序
	 */
	private void sortByScore(List<Product> products) {
		// 使用Collections.sort方法对产品列表进行排序
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o2.getProductScore().compareTo(o1.getProductScore());
			}
		});
	}

	private void sortByUpperPrice(List<Product> products) {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				Double price1 = Double.valueOf(o1.getPrice());
				Double price2 = Double.valueOf(o2.getPrice());
				return price2.compareTo(price1);
			}
		});
	}

	private void sortByLowerPrice(List<Product> products) {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				Double price1 = Double.valueOf(o1.getPrice());
				Double price2 = Double.valueOf(o2.getPrice());
				return price1.compareTo(price2);
			}
		});
	}

	private void sortBySellCount(List<Product> products) {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o2, Product o1) {
				o1.setReviewCount(ValidateUtils.validateCount(o1.getReviewCount()));
				o2.setReviewCount(ValidateUtils.validateCount(o2.getReviewCount()));
				Double count1 = Double.valueOf(o1.getReviewCount());
				Double count2 = Double.valueOf(o2.getReviewCount());
				return count1.compareTo(count2);
			}
		});
	}

	/**
	 * @param document analyzer highlighter
	 * @return Product
	 * @Description: 设置商品信息
	 */
	private Product setProduct(Document document, Analyzer analyzer, Highlighter highlighter) {
		Product productTemp = new Product();
		try {
			productTemp.setProductId(document.get("productId"));
		} catch (Exception e) {
			productTemp.setProductId("");
		}
		try {
			String productName = document.get("productName");
			//显示高亮部分
			if (productName != null) {
				TokenStream tokenStream = analyzer.tokenStream("productName", new StringReader(productName));
				productName = highlighter.getBestFragment(tokenStream, productName);
				productTemp.setProductName(productName);
			}
		} catch (Exception e) {
			productTemp.setProductName("");
		}
		try {
			productTemp.setCategoryName(document.get("categoryName"));
		} catch (Exception e) {
			productTemp.setCategoryName("");
		}
		try {
			productTemp.setSellCount(document.get("sellCount"));
		} catch (Exception e) {
			productTemp.setSellCount("");
		}
		try {
			productTemp.setReviewCount(document.get("reviewCount"));
		} catch (Exception e) {
			productTemp.setReviewCount("");
		}
		try {
			productTemp.setStock(Integer.parseInt(document.get("stock")));
		} catch (Exception e) {
			productTemp.setStock(0);
		}
		try {
			productTemp.setShopId(document.get("shopId"));
		} catch (Exception e) {
			productTemp.setShopId("");
		}
		try {
			productTemp.setShopName(document.get("shopName"));
		} catch (Exception e) {
			productTemp.setShopName("");
		}
		try {
			productTemp.setShopdsrMs(Double.parseDouble(document.get("shopdsrMs")));
		} catch (Exception e) {
			productTemp.setShopdsrMs(0.0);
		}
		try {
			productTemp.setShopdsrFw(Double.parseDouble(document.get("shopdsrFw")));
		} catch (Exception e) {
			productTemp.setShopdsrFw(0.0);
		}
		try {
			productTemp.setShopdsrWl(Double.parseDouble(document.get("shopdsrWl")));
		} catch (Exception e) {
			productTemp.setShopdsrWl(0.0);
		}
		try {
			productTemp.setProductUrl(document.get("productUrl"));
		} catch (Exception e) {
			productTemp.setProductUrl("");
		}
		try {
			productTemp.setProductDetail(document.get("productDetail"));
		} catch (Exception e) {
			productTemp.setProductDetail("");
		}
		try {
			productTemp.setProductScore(Double.parseDouble(document.get("productScore")));
		} catch (Exception e) {
			productTemp.setProductScore(0.0);
		}
		try {
			productTemp.setRecommendedReason(document.get("recommendedReason"));
		} catch (Exception e) {
			productTemp.setRecommendedReason("");
		}
		try {
			productTemp.setPrice(document.get("price"));
		} catch (Exception e) {
			productTemp.setPrice("");
		}
		try {
			productTemp.setProductImg(document.get("productImg"));
		} catch (Exception e) {
			productTemp.setProductImg("");
		}
		try {
			productTemp.setUpdateTime(document.get("updateTime"));
		} catch (Exception e) {
			productTemp.setUpdateTime("");
		}
		try {
			productTemp.setSpareField1(document.get("spareField1"));
		} catch (Exception e) {
			productTemp.setSpareField1("");
		}
		try {
			productTemp.setSpareField2(document.get("spareField2"));
		} catch (Exception e) {
			productTemp.setSpareField2("");
		}
		try {
			productTemp.setSpareField3(document.get("spareField3"));
		} catch (Exception e) {
			productTemp.setSpareField3("");
		}
		return productTemp;
	}


	public static Date timestampToDate(String date) {
		Date dateTime = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
		try {
			dateTime = simpleDateFormat.parse(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

}
