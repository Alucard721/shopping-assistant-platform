package com.niit.recommendation.entity;

import javax.persistence.*;

@Entity
public class Indicators1 {
	private int id;
	private String indicators1Name;
	private Double indicators1Weight;
	private String spareField1;

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
	 * 获取indicators1Name
	 *
	 * @return indicators1Name
	 */
	@Basic
	@Column(name = "Indicators_1_name")
	public String getIndicators1Name() {
		return indicators1Name;
	}

	/**
	 * 设置indicators1Name
	 *
	 * @param indicators1Name indicators1Name
	 */
	public void setIndicators1Name(String indicators1Name) {
		this.indicators1Name = indicators1Name;
	}

	/**
	 * 获取indicators1Weight
	 *
	 * @return indicators1Weight
	 */
	@Basic
	@Column(name = "Indicators_1_weight")
	public Double getIndicators1Weight() {
		return indicators1Weight;
	}

	/**
	 * 设置indicators1Weight
	 *
	 * @param indicators1Weight indicators1Weight
	 */
	public void setIndicators1Weight(Double indicators1Weight) {
		this.indicators1Weight = indicators1Weight;
	}

	/**
	 * 获取spareField1
	 *
	 * @return spareField1
	 */
	@Basic
	@Column(name = "spare_field1")
	public String getSpareField1() {
		return spareField1;
	}

	/**
	 * 设置spareField1
	 *
	 * @param spareField1 spareField1
	 */
	public void setSpareField1(String spareField1) {
		this.spareField1 = spareField1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Indicators1 that = (Indicators1) o;

		if (id != that.id) return false;
		if (indicators1Name != null ? !indicators1Name.equals(that.indicators1Name) : that.indicators1Name != null)
			return false;
		if (indicators1Weight != null ? !indicators1Weight.equals(that.indicators1Weight) : that.indicators1Weight != null)
			return false;
		if (spareField1 != null ? !spareField1.equals(that.spareField1) : that.spareField1 != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (indicators1Name != null ? indicators1Name.hashCode() : 0);
		result = 31 * result + (indicators1Weight != null ? indicators1Weight.hashCode() : 0);
		result = 31 * result + (spareField1 != null ? spareField1.hashCode() : 0);
		return result;
	}
}
