package com.niit.recommendation.utils;

import org.springframework.util.StringUtils;

public class ValidateUtils {

	public static String validateCount(String count){
		// 为空 暂赋默认值
		if(!StringUtils.isEmpty(count)){
			if(count.contains("+")){
				String tempCount1 = count.substring(0,count.length()-1); //除+号
				if(tempCount1.contains("万")){
					String tempCount2 = tempCount1.substring(0,tempCount1.length()-1);
					return String.valueOf(Double.parseDouble(tempCount2)*10000); //如 7.0万+
				}else{
					return tempCount1; //如7000+
				}
			}else{
				return count;
			}
		}else{
			return "100";
		}
	}
}
