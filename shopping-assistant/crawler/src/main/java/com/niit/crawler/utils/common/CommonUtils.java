package com.niit.crawler.utils.common;

import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonUtils{

	public static <T> void print(List<T> element){
		if(element instanceof WebElement){
			for(T  a: element){
				WebElement b = (WebElement)a;
				System.out.println(b.getText());
			}
		}

	}
}
