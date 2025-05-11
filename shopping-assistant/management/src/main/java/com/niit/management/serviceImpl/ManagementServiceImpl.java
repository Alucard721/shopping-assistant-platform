package com.niit.management.serviceImpl;

import com.niit.management.api.DTO.IndicatorDTO;
import com.niit.management.api.DTO.Indicators2DTO;
import com.niit.management.api.DTO.PageRequest;
import com.niit.management.api.DTO.ResponseDTO;
import com.niit.management.entity.Indicators1;
import com.niit.management.entity.Indicators2;
import com.niit.management.entity.Product;
import com.niit.management.repository.Indicators1Repository;
import com.niit.management.repository.Indicators2Repository;
import com.niit.management.repository.ProductRepository;
import com.niit.management.service.ManagementService;
import com.niit.management.utils.ResponseUtil;
import com.niit.management.utils.Validation.ValidationUtil;
import com.niit.management.utils.Validation.groups.InvalidProductGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class ManagementServiceImpl implements ManagementService {
	private static Logger logger = LoggerFactory.getLogger(ManagementServiceImpl.class);


	@Autowired
	Indicators1Repository indicators1Repository;

	@Autowired
	Indicators2Repository indicators2Repository;

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public IndicatorDTO findEvaluateModule() {

		//统一两个指标源为同一格式IndicatorDTO
		List<Indicators1> indicators1s = indicators1Repository.findAll();
		List<IndicatorDTO> indicatorDTOS = new ArrayList<IndicatorDTO>();
		for (Indicators1 indicators1 : indicators1s) {
			indicatorDTOS.add(indicatorToDto(indicators1, 1));
		}
		List<Indicators2> indicators2s = indicators2Repository.findAll();
		for (Indicators2 indicators2 : indicators2s) {
			indicatorDTOS.add(indicatorToDto(indicators2, 2));
		}

		//暂时无用
		Iterator iterator = indicatorDTOS.iterator();
		List<IndicatorDTO> indicatorDTOsStep1 = new ArrayList<IndicatorDTO>();
		List<IndicatorDTO> indicatorDTOsStep2 = new ArrayList<IndicatorDTO>();

		List<IndicatorDTO> indicatorDTOSTemp = new ArrayList<IndicatorDTO>();
		for (Indicators1 indicators1 : indicators1s) {
			indicatorDTOSTemp.add(indicatorToDto(indicators1, 1));
		}
		// 创建一个顶级指标，其子指标列表是上面的indicatorDTOSTemp
		IndicatorDTO indicatorDTO = new IndicatorDTO("评价模型", 1.0, 0, 0, indicatorDTOSTemp);
		// 为每个一级指标设置二级子指标
		for (IndicatorDTO indicatorDTO1 : indicatorDTO.getChildren()) {
			indicatorDTO1.setChildren(iterator(indicatorDTOS.iterator(), 2, indicatorDTO1));
		}

		//二级指标后面没有了
		for (IndicatorDTO indicatorDTO1 : indicatorDTO.getChildren()) {
			for (IndicatorDTO indicatorDTO2 : indicatorDTO1.getChildren())
				indicatorDTO2.setChildren(iterator(indicatorDTOS.iterator(), 3, indicatorDTO1));
		}
		return indicatorDTO;
	}

	@Override
	@Transactional
	public ResponseDTO saveEvaluate(Indicators2DTO indicators2DTO) {
		List<Indicators2> indicators2s = indicators2Repository.findAll();
		for (Indicators2 indicators2 : indicators2s) {
			if ("销量".equals(indicators2.getIndicators2Name())) {
				indicators2.setIndicators2Weight(indicators2DTO.getSellCount());
			} else if ("评论量".equals(indicators2.getIndicators2Name())) {
				indicators2.setIndicators2Weight(indicators2DTO.getReviewCount());
			} else if ("描述评分".equals(indicators2.getIndicators2Name())) {
				indicators2.setIndicators2Weight(indicators2DTO.getShopdsrMs());
			} else if ("服务评分".equals(indicators2.getIndicators2Name())) {
				indicators2.setIndicators2Weight(indicators2DTO.getShopdsrFw());
			} else if ("物流评分".equals(indicators2.getIndicators2Name())) {
				indicators2.setIndicators2Weight(indicators2DTO.getShopdsrWl());
			}
		}
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			indicators2Repository.saveAll(indicators2s);
		} catch (Exception e) {
			responseDTO.setCode("0");
			responseDTO.setReturnMessage("保存失败");
			return responseDTO;
		}
		responseDTO.setCode("1");
		responseDTO.setReturnMessage("保存成功");
		return responseDTO;
	}

	@Override
	@Transactional
	public PageRequest<Product> validateProduct(Integer pageNum, Integer pageSize) {
//		找到所有spare_field3内有违规理由的商品
		List<Product> invalidProducts = productRepository.findAllInvalidProducts();
		PageRequest<Product> pageRequest = new PageRequest<Product>();
		if (pageNum == 0) {
			pageRequest.setFirst(true);
		}
		if (invalidProducts.size() / pageSize == pageNum) {
			pageRequest.setHasNext(false);
		}
		pageRequest.setContent(invalidProducts.subList(pageNum * pageSize, invalidProducts.size() - pageNum * pageSize > pageSize ? (pageNum + 1) * pageSize : invalidProducts.size()));
		pageRequest.setPageNum(pageNum);
		pageRequest.setPageSize(pageSize);
		return pageRequest;
	}

	@Override
	@Transactional
	public ResponseDTO updatavalidateProduct() {
		List<Product> products = productRepository.findAll();
		List<Product> invalidProducts = new ArrayList<Product>();

		for (Product product : products) {
			try {
				ValidationUtil.validate(product, InvalidProductGroup.class);
			} catch (ValidationException e) {
				product.setSpareField3(e.getMessage());
				invalidProducts.add(product);
			}
		}
		try {
			productRepository.saveAll(invalidProducts);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.fail();
		}
		return ResponseUtil.success();
	}

	@Override
	@Transactional
	public ResponseDTO saveProduct(Product product) {
		try {
			product.setSpareField3("");
			productRepository.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.fail();
		}
		return ResponseUtil.success();
	}

	@Override
	@Transactional
	public ResponseDTO deleteInvalid(Integer id) {
		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			return ResponseUtil.fail();
		}
		return ResponseUtil.success();
	}

	@Override
	public void clearInvalid() {
		List<Product> products = productRepository.findAll();
		List<Product> productsBatch = new ArrayList<Product>();
		for (Product product : products) {
			product.setSpareField3("");
			productsBatch.add(product);
			if(productsBatch.size()==1000){
				productRepository.saveAll(productsBatch);
				productsBatch.clear();
			}
		}
	}

	private List<IndicatorDTO> iterator(Iterator iterator, Integer step, IndicatorDTO indicatorDTO) {
		// 创建一个IndicatorDTO列表，用于存储筛选后的指标
		List<IndicatorDTO> indicatorDTOS = new ArrayList<IndicatorDTO>();

		// 遍历迭代器中的所有元素
		while (iterator.hasNext()) {
			// 将迭代器当前元素转换为IndicatorDTO类型
			IndicatorDTO indicatorDTOTemp = (IndicatorDTO) iterator.next();

			// 如果当前元素的层级与传入的step相同，并且id与父指标的id相同
			if (step.equals(indicatorDTOTemp.getStep()) && indicatorDTOTemp.getId().equals(indicatorDTO.getId())) {
				// 将符合条件的指标添加到列表中
				indicatorDTOS.add(indicatorDTOTemp);
				// 从迭代器中移除当前元素，避免重复处理
				iterator.remove();
			}
		}
		// 返回筛选后的指标列表
		return indicatorDTOS;
	}

	private IndicatorDTO indicatorToDto(Object o, Integer step) {
		IndicatorDTO indicatorDTO = new IndicatorDTO();
		Class c = o.getClass();
		Field[] declareFields = c.getDeclaredFields();
		try {
			for (Field field : declareFields) {
				field.setAccessible(true); //
				if (field.getName().endsWith("Name")) {
					indicatorDTO.setName((String) field.get(o));
				} else if (field.getName().endsWith("Weight")) {
					indicatorDTO.setWeight((Double) field.get(o));
				} else if (field.getName().endsWith("Id")) {
					indicatorDTO.setId((Integer) field.get(o));
				}
				if (step == 1 && "id".equals(field.getName())) {
					indicatorDTO.setId((Integer) field.get(o));
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.error("indicatorToDto转换失败");
		}
		indicatorDTO.setStep(step);
		return indicatorDTO;
	}
}
