package com.niit.management.controller;


import com.niit.management.api.DTO.IndicatorDTO;
import com.niit.management.api.DTO.Indicators2DTO;
import com.niit.management.api.DTO.PageRequest;
import com.niit.management.api.DTO.ResponseDTO;
import com.niit.management.entity.Product;
import com.niit.management.entity.User;
import com.niit.management.service.ManagementService;
import com.niit.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/management")
public class ManagementController {

	private static Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@Autowired
	ManagementService managementService;


	/**
	 * @Description: 页面跳转
	 * @param pageName
	 * @return String
	 */
	@RequestMapping("/{pageName}")
	public String toIndex(@PathVariable("pageName") String pageName) {
		return pageName;
	}

	/**
	 * @Description: 获取评价模型
	 * @return IndicatorDTO
	 */
	@RequestMapping(value = "/findEvaluateModule", method = RequestMethod.POST)
//	将方法的返回值写入HTTP响应体中
	@ResponseBody
	public IndicatorDTO findEvaluateModule(){
//		managementService.clearInvalid();
		return managementService.findEvaluateModule();
	}

	/**
	 * @Description: 评价模型修改
	 * @param indicators2DTO
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/saveEvaluate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO saveEvaluate(@RequestBody Indicators2DTO indicators2DTO){
		System.out.println(indicators2DTO);
		return managementService.saveEvaluate(indicators2DTO);
	}

	/**
	 * @Description: 校验商品信息完整
	 * @param pageNum pageSize
	 * @return 输出参数
	 */
	@RequestMapping(value = "/validateProduct", method = RequestMethod.POST)
	@ResponseBody
	PageRequest<Product> validateProduct(Integer pageNum, Integer pageSize){
		return managementService.validateProduct(pageNum,pageSize);
	}

	/**
	 * @Description: 更新非法商品信息
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/updatavalidateProduct", method = RequestMethod.POST)
	@ResponseBody
	ResponseDTO updatavalidateProduct(){
		return managementService.updatavalidateProduct();
	}

	/**
	 * @Description: 保存商品信息
	 * @param product
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO saveProduct(@RequestBody Product product){
		return managementService.saveProduct(product);
	}

	/**
	 * @Description: 删除非法商品信息
	 * @param id
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "/deleteInvalid", method = RequestMethod.POST)
	@ResponseBody
	ResponseDTO deleteInvalid(Integer id){
		return managementService.deleteInvalid(id);

	}


}
