package com.niit.management.api.DTO;

import java.util.ArrayList;
import java.util.List;

public class IndicatorDTO {

	private String name;
	private Double weight;
	private Integer id = 0;
	private Integer step;
	List<IndicatorDTO> children = new ArrayList<IndicatorDTO>();

	/**
	 * 获取name
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 *
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取weight
	 *
	 * @return weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * 设置weight
	 *
	 * @param weight weight
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * 获取id
	 *
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置id
	 *
	 * @param id id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取step
	 *
	 * @return step
	 */
	public Integer getStep() {
		return step;
	}

	/**
	 * 设置step
	 *
	 * @param step step
	 */
	public void setStep(Integer step) {
		this.step = step;
	}

	/**
	 * 获取children
	 *
	 * @return children
	 */
	public List<IndicatorDTO> getChildren() {
		return children;
	}

	/**
	 * 设置children
	 *
	 * @param children children
	 */
	public void setChildren(List<IndicatorDTO> children) {
		this.children = children;
	}

	public IndicatorDTO() {
	}

	public IndicatorDTO(String name, Double weight, Integer id, Integer step, List<IndicatorDTO> children) {
		this.name = name;
		this.weight = weight;
		this.id = id;
		this.step = step;
		this.children = children;
	}

}
