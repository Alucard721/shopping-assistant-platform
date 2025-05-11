package com.niit.management.entity;

import javax.persistence.*;

@Entity
public class Indicators2 {
	private int id;
	private String indicators2Name;
	private Double indicators2Weight;
	private Integer indicators1Id;
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
	 * 获取indicators2Name
	 *
	 * @return indicators2Name
	 */
	@Basic
	@Column(name = "Indicators_2_name")
	public String getIndicators2Name() {
		return indicators2Name;
	}

	/**
	 * 设置indicators2Name
	 *
	 * @param indicators2Name indicators2Name
	 */
	public void setIndicators2Name(String indicators2Name) {
		this.indicators2Name = indicators2Name;
	}

	/**
	 * 获取indicators2Weight
	 *
	 * @return indicators2Weight
	 */
	@Basic
	@Column(name = "Indicators_2_weight")
	public Double getIndicators2Weight() {
		return indicators2Weight;
	}

	/**
	 * 设置indicators2Weight
	 *
	 * @param indicators2Weight indicators2Weight
	 */
	public void setIndicators2Weight(Double indicators2Weight) {
		this.indicators2Weight = indicators2Weight;
	}

	/**
	 * 获取indicators1Id
	 *
	 * @return indicators1Id
	 */
	@Basic
	@Column(name = "Indicators_1_id")
	public Integer getIndicators1Id() {
		return indicators1Id;
	}

	/**
	 * 设置indicators1Id
	 *
	 * @param indicators1Id indicators1Id
	 */
	public void setIndicators1Id(Integer indicators1Id) {
		this.indicators1Id = indicators1Id;
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

		Indicators2 that = (Indicators2) o;

		if (id != that.id) return false;
		if (indicators2Name != null ? !indicators2Name.equals(that.indicators2Name) : that.indicators2Name != null)
			return false;
		if (indicators2Weight != null ? !indicators2Weight.equals(that.indicators2Weight) : that.indicators2Weight != null)
			return false;
		if (indicators1Id != null ? !indicators1Id.equals(that.indicators1Id) : that.indicators1Id != null) return false;
		if (spareField1 != null ? !spareField1.equals(that.spareField1) : that.spareField1 != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (indicators2Name != null ? indicators2Name.hashCode() : 0);
		result = 31 * result + (indicators2Weight != null ? indicators2Weight.hashCode() : 0);
		result = 31 * result + (indicators1Id != null ? indicators1Id.hashCode() : 0);
		result = 31 * result + (spareField1 != null ? spareField1.hashCode() : 0);
		return result;
	}
}
