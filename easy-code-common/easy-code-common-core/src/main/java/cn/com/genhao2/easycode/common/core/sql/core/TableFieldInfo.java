package cn.com.genhao2.easycode.common.core.sql.core;

import cn.com.genhao2.easycode.common.core.sql.core.enums.IdType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 数据库表字段
 *
 * @author luyanan
 * @since 2022/11/26
 **/
@Getter
@Setter
@Accessors(chain = true)
public class TableFieldInfo implements Serializable {


	/**
	 * 是否为主键
	 *
	 * @since 2022/11/28
	 */

	private boolean primaryKey = false;
	/**
	 * 备注
	 *
	 * @since 2022/11/27
	 */

	private String comment;
	/**
	 * 数据库类型
	 *
	 * @since 2022/11/27
	 */

	private String jdbcType;

	/**
	 * jdbc的字段
	 *
	 * @since 2022/11/28
	 */

	private String jdbcColumn;


	/**
	 * java类型
	 *
	 * @since 2022/11/28
	 */

	private Class<?> javaType;


	/**
	 * java字段
	 *
	 * @since 2022/11/28
	 */

	private String javaColumn;
}
