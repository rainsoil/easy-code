package cn.com.genhao2.easycode.common.core.sql.core;

import cn.hutool.core.text.NamingCase;
import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

/**
 * 数据库表到java 字段的命名策略
 *
 * @author luyanan
 * @since 2022/11/27
 **/
public enum NamingStrategy {


	/**
	 * 不做任何改变
	 *
	 * @since 2022/11/27
	 */

	NO_CHANGE,
	/**
	 * 下划线转驼峰
	 *
	 * @since 2022/11/27
	 */

	UNDERLINE_TO_CAMEL,
	;


	/**
	 * 下划线转驼峰
	 *
	 * @param name 转换内容
	 * @return java.lang.String
	 * @since 2022/11/27
	 */
	public  String underlineToCamel(String name) {
		// 快速检查
		if (StrUtil.isBlank(name)) {
			// 没必要转换
			return StrUtil.EMPTY;
		}

		return NamingCase.toCamelCase(name);
	}

	/**
	 * 驼峰转下划线
	 *
	 * @param name 转换内容
	 * @return java.lang.String
	 * @since 2022/11/27
	 */
	public  String camelToUnderline(String name) {
		return NamingCase.toUnderlineCase(name);
	}

}
