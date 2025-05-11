package com.niit.interact.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Product {
	private int id;
	private String productId;
	private String productName;
	private String price;
	private String categoryName;
	private Integer sellCount;
	private String reviewCount;
	private Integer stock;
	private String shopId;
	private String shopName;
	private String productImg;
	private Double shopdsrMs;
	private Double shopdsrFw;
	private Double shopdsrWl;
	private String productUrl;
	private String productDetail;
	private Double productScore;
	private String recommendedReason;
	private String updateTime;
	private double spareField1;
	private String spareField2;
	private String spareField3;

	/**
	 * 获取id
	 *
	 * @return id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	/**
	 * 设置id
	 *
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取productId
	 *
	 * @return productId
	 */
	@Basic
	@Column(name = "product_id")
	public String getProductId() {
		return productId;
	}

	/**
	 * 设置productId
	 *
	 * @param productId productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 获取productName
	 *
	 * @return productName
	 */
	@Basic
	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}

	/**
	 * 设置productName
	 *
	 * @param productName productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 获取price
	 *
	 * @return price
	 */
	@Basic
	@Column(name = "price")
	public String getPrice() {
		return price;
	}

	/**
	 * 设置price
	 *
	 * @param price price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * 获取categoryName
	 *
	 * @return categoryName
	 */
	@Basic
	@Column(name = "category_name")
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 设置categoryName
	 *
	 * @param categoryName categoryName
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 获取sellCount
	 *
	 * @return sellCount
	 */
	@Basic
	@Column(name = "sell_count")
	public Integer getSellCount() {
		return sellCount;
	}

	/**
	 * 设置sellCount
	 *
	 * @param sellCount sellCount
	 */
	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

	/**
	 * 获取reviewCount
	 *
	 * @return reviewCount
	 */
	@Basic
	@Column(name = "review_count")
	public String getReviewCount() {
		return reviewCount;
	}

	/**
	 * 设置reviewCount
	 *
	 * @param reviewCount reviewCount
	 */
	public void setReviewCount(String reviewCount) {
		this.reviewCount = reviewCount;
	}

	/**
	 * 获取stock
	 *
	 * @return stock
	 */
	@Basic
	@Column(name = "stock")
	public Integer getStock() {
		return stock;
	}

	/**
	 * 设置stock
	 *
	 * @param stock stock
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * 获取deliveryAdd
	 *
	 * @return deliveryAdd
	 */

	/**
	 * 获取shopId
	 *
	 * @return shopId
	 */
	@Basic
	@Column(name = "shop_id")
	public String getShopId() {
		return shopId;
	}

	/**
	 * 设置shopId
	 *
	 * @param shopId shopId
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * 获取shopName
	 *
	 * @return shopName
	 */
	@Basic
	@Column(name = "shop_name")
	public String getShopName() {
		return shopName;
	}

	/**
	 * 设置shopName
	 *
	 * @param shopName shopName
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * 获取productImg
	 *
	 * @return productImg
	 */
	@Basic
	@Column(name = "product_img")
	public String getProductImg() {
		return productImg;
	}

	/**
	 * 设置productImg
	 *
	 * @param productImg productImg
	 */
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	/**
	 * 获取shopdsrMs
	 *
	 * @return shopdsrMs
	 */
	@Basic
	@Column(name = "shopdsr_ms")
	public Double getShopdsrMs() {
		return shopdsrMs;
	}

	/**
	 * 设置shopdsrMs
	 *
	 * @param shopdsrMs shopdsrMs
	 */
	public void setShopdsrMs(Double shopdsrMs) {
		this.shopdsrMs = shopdsrMs;
	}

	/**
	 * 获取shopdsrFw
	 *
	 * @return shopdsrFw
	 */
	@Basic
	@Column(name = "shopdsr_fw")
	public Double getShopdsrFw() {
		return shopdsrFw;
	}

	/**
	 * 设置shopdsrFw
	 *
	 * @param shopdsrFw shopdsrFw
	 */
	public void setShopdsrFw(Double shopdsrFw) {
		this.shopdsrFw = shopdsrFw;
	}

	/**
	 * 获取shopdsrWl
	 *
	 * @return shopdsrWl
	 */
	@Basic
	@Column(name = "shopdsr_wl")
	public Double getShopdsrWl() {
		return shopdsrWl;
	}

	/**
	 * 设置shopdsrWl
	 *
	 * @param shopdsrWl shopdsrWl
	 */
	public void setShopdsrWl(Double shopdsrWl) {
		this.shopdsrWl = shopdsrWl;
	}

	/**
	 * 获取productSku
	 *
	 * @return productSku
	 */

	/**
	 * 获取productUrl
	 *
	 * @return productUrl
	 */
	@Basic
	@Column(name = "product_url")
	public String getProductUrl() {
		return productUrl;
	}

	/**
	 * 设置productUrl
	 *
	 * @param productUrl productUrl
	 */
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	/**
	 * 获取productDetail
	 *
	 * @return productDetail
	 */
	@Basic
	@Column(name = "product_detail")
	public String getProductDetail() {
		return productDetail;
	}

	/**
	 * 设置productDetail
	 *
	 * @param productDetail productDetail
	 */
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	/**
	 * 获取productScore
	 *
	 * @return productScore
	 */
	@Basic
	@Column(name = "product_score")
	public Double getProductScore() {
		return productScore;
	}

	/**
	 * 设置productScore
	 *
	 * @param productScore productScore
	 */
	public void setProductScore(Double productScore) {
		this.productScore = productScore;
	}

	/**
	 * 获取recommendedReason
	 *
	 * @return recommendedReason
	 */
	@Basic
	@Column(name = "recommended_reason")
	public String getRecommendedReason() {
		return recommendedReason;
	}

	/**
	 * 设置recommendedReason
	 *
	 * @param recommendedReason recommendedReason
	 */
	public void setRecommendedReason(String recommendedReason) {
		this.recommendedReason = recommendedReason;
	}

	/**
	 * 获取updateTime
	 *
	 * @return updateTime
	 */
	@Basic
	@Column(name = "update_time")
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置updateTime
	 *
	 * @param updateTime updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取spareField1
	 *
	 * @return spareField1
	 */
	@Basic
	@Column(name = "spare_field1")
	public double getSpareField1() {
		return spareField1;
	}

	/**
	 * 设置spareField1
	 *
	 * @param spareField1 spareField1
	 */
	public void setSpareField1(double spareField1) {
		this.spareField1 = spareField1;
	}

	/**
	 * 获取spareField2
	 *
	 * @return spareField2
	 */
	@Basic
	@Column(name = "spare_field2")
	public String getSpareField2() {
		return spareField2;
	}

	/**
	 * 设置spareField2
	 *
	 * @param spareField2 spareField2
	 */
	public void setSpareField2(String spareField2) {
		this.spareField2 = spareField2;
	}

	/**
	 * 获取spareField3
	 *
	 * @return spareField3
	 */
	@Basic
	@Column(name = "spare_field3")
	public String getSpareField3() {
		return spareField3;
	}

	/**
	 * 设置spareField3
	 *
	 * @param spareField3 spareField3
	 */
	public void setSpareField3(String spareField3) {
		this.spareField3 = spareField3;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

