package com.niit.search.api.DTO;


import com.niit.search.entity.Product;
import java.io.Serializable;
import java.util.List;

public class SearchResponseDTO implements Serializable {

   private List<Product> products;

   private Integer total;

   private Integer totalPage;

	/**
	 * 获取products
	 *
	 * @return products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * 设置products
	 *
	 * @param products products
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/**
	 * 获取total
	 *
	 * @return total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * 设置total
	 *
	 * @param total total
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 获取totalPage
	 *
	 * @return totalPage
	 */
	public Integer getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置totalPage
	 *
	 * @param totalPage totalPage
	 */
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public SearchResponseDTO() {
	}

}
