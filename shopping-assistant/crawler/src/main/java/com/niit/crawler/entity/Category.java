package com.niit.crawler.entity;

import javax.persistence.*;

@Entity
public class Category {
	private int id;
	private String categoryId;
	private String category1Name;
	private String category2Name;
	private String category3Name;

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
	 * 获取categoryId
	 *
	 * @return categoryId
	 */
	@Basic
	@Column(name = "category_id")
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * 设置categoryId
	 *
	 * @param categoryId categoryId
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 获取category1Name
	 *
	 * @return category1Name
	 */
	@Basic
	@Column(name = "category1_name")
	public String getCategory1Name() {
		return category1Name;
	}

	/**
	 * 设置category1Name
	 *
	 * @param category1Name category1Name
	 */
	public void setCategory1Name(String category1Name) {
		this.category1Name = category1Name;
	}

	/**
	 * 获取category2Name
	 *
	 * @return category2Name
	 */
	@Basic
	@Column(name = "category2_name")
	public String getCategory2Name() {
		return category2Name;
	}

	/**
	 * 设置category2Name
	 *
	 * @param category2Name category2Name
	 */
	public void setCategory2Name(String category2Name) {
		this.category2Name = category2Name;
	}

	/**
	 * 获取category3Name
	 *
	 * @return category3Name
	 */
	@Basic
	@Column(name = "category3_name")
	public String getCategory3Name() {
		return category3Name;
	}

	/**
	 * 设置category3Name
	 *
	 * @param category3Name category3Name
	 */
	public void setCategory3Name(String category3Name) {
		this.category3Name = category3Name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Category category = (Category) o;

		if (id != category.id) return false;
		if (categoryId != null ? !categoryId.equals(category.categoryId) : category.categoryId != null) return false;
		if (category1Name != null ? !category1Name.equals(category.category1Name) : category.category1Name != null)
			return false;
		if (category2Name != null ? !category2Name.equals(category.category2Name) : category.category2Name != null)
			return false;
		if (category3Name != null ? !category3Name.equals(category.category3Name) : category.category3Name != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
		result = 31 * result + (category1Name != null ? category1Name.hashCode() : 0);
		result = 31 * result + (category2Name != null ? category2Name.hashCode() : 0);
		result = 31 * result + (category3Name != null ? category3Name.hashCode() : 0);
		return result;
	}

	public Category() {}

	public Category(String categoryId, String category1Name, String category2Name, String category3Name) {
		this.categoryId = categoryId;
		this.category1Name = category1Name;
		this.category2Name = category2Name;
		this.category3Name = category3Name;
	}
}
