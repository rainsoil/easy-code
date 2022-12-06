package cn.com.genhao2.easycode.common.core.sql.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 生成ID类型枚举类
 *
 * @author luyanan
 * @since 2022/11/26
 **/
@Getter
@AllArgsConstructor
public enum IdType {

	/**
	 * 数据库自增
	 *
	 * @since 2022/11/26
	 */
	AUTO(0),

	/**
	 * 用户自动填充
	 *
	 * @since 2022/11/26
	 */

	INPUT(1),

	NONE(2),
	;
	/**
	 * 主键类型
	 *
	 * @since 2022/11/26
	 */

	private final int key;


}
