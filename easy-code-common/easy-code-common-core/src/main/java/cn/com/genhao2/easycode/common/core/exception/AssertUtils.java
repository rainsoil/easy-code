package cn.com.genhao2.easycode.common.core.exception;

import cn.hutool.core.lang.Assert;
import lombok.experimental.UtilityClass;

/**
 * 断言
 *
 * @author luyanan
 * @since 2022/10/20
 **/
@UtilityClass
public class AssertUtils extends Assert {

	/**
	 * 是否为真
	 *
	 * @param expression 布尔值
	 * @param errorCode  错误代码
	 * @since 2022/10/20
	 */
	public void isTrue(boolean expression, Integer errorCode) {

		if (!expression) {
			throw new BaseException(errorCode);
		}
	}


	/**
	 * 是否为真
	 *
	 * @param expression 布尔值
	 * @param errorMsg   错误信息
	 * @since 2022/10/20
	 */
	public void isTrue(boolean expression, String errorMsg) {

		if (!expression) {
			throw new BaseException(errorMsg);
		}
	}

	/**
	 * 是否为假
	 *
	 * @param expression 布尔值
	 * @param errorCode  错误代码
	 * @since 2022/10/20
	 */
	public void isFalse(boolean expression, Integer errorCode) {

		if (expression) {
			throw new BaseException(errorCode);
		}
	}


	/**
	 * 是否为假
	 *
	 * @param expression 布尔值
	 * @param errorMsg   错误信息
	 * @since 2022/10/20
	 */
	public void isFalse(boolean expression, String errorMsg) {

		if (expression) {
			throw new BaseException(errorMsg);
		}
	}


}
