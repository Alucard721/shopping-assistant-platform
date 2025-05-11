package com.niit.crawler.utils;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {

	private static Validator validator;

	static {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	public static <T> void validate(T t) throws ValidationException {
		Set<ConstraintViolation<T>> set = validator.validate(t);
		StringBuilder validateError = new StringBuilder();
		if(set.size()>0){
			for(ConstraintViolation constraintViolation : set){
				validateError.append(constraintViolation.getMessage()+";");
			}
			throw new ValidationException(validateError.toString());
		}
	}
}
