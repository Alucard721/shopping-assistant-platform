package com.niit.search.serviceImpl;

import com.niit.search.entity.Product;
import com.niit.search.repository.ProductRepository;
import com.niit.search.utils.DeleteFileUtil;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class IndexServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
	private static String DIR_PATH = "D:\\code\\Graduationproject\\LuceneDocument";

	@Autowired
	private ProductRepository productRepository;

	/**
	 * 建立倒排索引，即按照关键词对数据创建文档，并记录包含此关键词的文档列表
	 * 则在搜索时可以通过关键词快速找到包含关键词的数据列表
	 */
	public void indexAll() {
		// 删除原有索引
		DeleteFileUtil.deleteDirectory(DIR_PATH);
		// 建立全量索引,CJKAnalyzer为一种支持中文的分词器,可以进行分词索引
		CJKAnalyzer ikAnalyzer = new CJKAnalyzer();
		IndexWriter indexWriter = null;
		try {
			Directory directory = FSDirectory.open(Paths.get(DIR_PATH));
			// 创建配置
			IndexWriterConfig config = new IndexWriterConfig(ikAnalyzer);
			//用上述的目录与配置对象来初始化writer
			indexWriter = new IndexWriter(directory, config);
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("文件读取错误---");
			try {
				indexWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		List<Product> products = productRepository.findAll();
		try {
			for (Product product : products) {
				// 为每个产品创建文档并添加到索引中
				Document document = new Document();
				//logger.info("{}添加索引:{}", product.getProductId(), product.getProductName());
				document.add(new TextField("productName", product.getProductName(), Field.Store.YES));
				document.add(new StringField("productId", product.getProductId(), Field.Store.YES));
				document.add(new StringField("price", product.getPrice()==null?"":product.getPrice(), Field.Store.YES));
				document.add(new StringField("categoryName", (product.getCategoryName()==null?"":product.getCategoryName()), Field.Store.YES));
				document.add(new StringField("sellCount", product.getSellCount()==null?"":product.getSellCount(), Field.Store.YES));
				document.add(new StringField("reviewCount", product.getReviewCount()==null?"":product.getReviewCount(), Field.Store.YES));
				document.add(new LegacyIntField("stock", product.getStock()==null?0:product.getStock(), Field.Store.YES));
				document.add(new StringField("shopId", product.getShopId()==null?"":product.getShopId(), Field.Store.YES));
				document.add(new TextField("shopName", product.getShopName()==null?"":product.getShopName(), Field.Store.YES));
				document.add(new StringField("productImg", product.getProductImg()==null?"":product.getProductImg(), Field.Store.YES));
				document.add(new LegacyDoubleField("shopdsrMs", product.getShopdsrMs()==null?0:product.getShopdsrMs(), Field.Store.YES));
				document.add(new LegacyDoubleField("shopdsrfw", product.getShopdsrFw()==null?0:product.getShopdsrFw(), Field.Store.YES));
				document.add(new LegacyDoubleField("shopdsrwl", product.getShopdsrWl()==null?0:product.getShopdsrWl(), Field.Store.YES));
				document.add(new StringField("productUrl", product.getProductUrl()==null?"":product.getProductUrl(), Field.Store.YES));
				document.add(new StringField("productDetail", product.getProductDetail()==null?"":product.getProductDetail(), Field.Store.YES));
				document.add(new LegacyDoubleField("productScore", product.getProductScore()==null?0:product.getProductScore(), Field.Store.YES));
				document.add(new StringField("recommendedReason", product.getRecommendedReason()==null?"":product.getRecommendedReason(), Field.Store.YES));
				document.add(new StringField("updateTime", product.getUpdateTime()==null?"":product.getUpdateTime(), Field.Store.YES));
				document.add(new StringField("spareField1", product.getSpareField1()==null?"":product.getSpareField1(), Field.Store.YES));
				document.add(new StringField("spareField2", product.getSpareField2()==null?"":product.getSpareField2(), Field.Store.YES));
				document.add(new StringField("spareField3", product.getSpareField3()==null?"":product.getSpareField3(), Field.Store.YES));
				indexWriter.addDocument(document);

			}
			// 提交更改
			indexWriter.commit();
			indexWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("文件写入错误--");
			try {
				indexWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		logger.info("--------索引结束:---");
	}
}
