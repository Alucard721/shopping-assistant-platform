package com.niit.management.service;

import com.niit.management.api.DTO.IndicatorDTO;
import com.niit.management.api.DTO.Indicators2DTO;
import com.niit.management.api.DTO.PageRequest;
import com.niit.management.api.DTO.ResponseDTO;
import com.niit.management.entity.Product;

//对商品数据的维护应该是只对关键的商品字段继续添加，不需要对数据继续修改
//并且应该设定当商品数据没有运行完成时，用户不能进入用户端
public interface ManagementService {



	/**
	 * @return IndicatorDTO
	 * @Description: 获取评价模型
	 */
	IndicatorDTO findEvaluateModule();

	/**
	 * @param indicators2DTO
	 * @return ResponseDTO
	 * @Description: 保存评价模型
	 */
	ResponseDTO saveEvaluate(Indicators2DTO indicators2DTO);

	/**
	 * @Description: 校验商品信息完整
	 * @param pageNum pageSize
	 * @return 输出参数
	 */
	PageRequest<Product> validateProduct(Integer pageNum, Integer pageSize);

	/**
	 * @Description: 更新非法商品信息
	 * @return ResponseDTO
	 */
	ResponseDTO updatavalidateProduct();

	/**
	 * @Description: 保存商品信息
	 * @param product
	 * @return ResponseDTO
	 */
	ResponseDTO saveProduct(Product product);

	/**
	 * @Description: 删除非法商品信息
	 * @param id
	 * @return ResponseDTO
	 */
	ResponseDTO deleteInvalid(Integer id);

	public void clearInvalid();
}
