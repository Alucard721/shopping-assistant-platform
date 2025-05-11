package com.niit.management.utils.Validation;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {

	private static Validator validator;

	static {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

//	激活校验组,校验一个对象是否符合指定的校验组
	public static <T> void validate(T t,Class<?>... groups) throws ValidationException {
		Set<ConstraintViolation<T>> set = validator.validate(t,groups);
		StringBuilder validateError = new StringBuilder();
		if(set.size()>0){
			for(ConstraintViolation constraintViolation : set){
				validateError.append(constraintViolation.getMessage()+";");
			}
			throw new ValidationException(validateError.toString());
		}
	}
}
