package com.niit.interact.api.DTO;

import com.niit.interact.entity.Product;

import java.io.Serializable;
import java.util.List;

public class SearchResponseDTO implements Serializable {

	private List<Product> products;

	private Integer total;

	private Integer totalPage;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public SearchResponseDTO() {
	}
}
