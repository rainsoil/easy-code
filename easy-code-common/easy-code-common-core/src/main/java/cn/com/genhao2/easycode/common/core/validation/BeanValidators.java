package cn.com.genhao2.easycode.common.core.validation;

import lombok.experimental.UtilityClass;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * 对象属性校验
 *
 * @author luyanan
 * @since 2022/8/9
 **/
@UtilityClass
public class BeanValidators {
	/**
	 * 属性校验
	 *
	 * @param validator validator
	 * @param object    对象
	 * @param groups    分组
	 * @throws ConstraintViolationException 验证不通过
	 * @since 2022/8/9
	 */
	public void validateWithException(Validator validator, Object object, Class<?>... groups)
			throws ConstraintViolationException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}
}
