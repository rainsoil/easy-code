package cn.com.genhao2.easycode.common.core.sql.core;

import cn.com.genhao2.easycode.common.core.sql.core.enums.IdType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 表信息
 *
 * @author luyanan
 * @since 2022/11/26
 **/

@Getter
@Setter
@Accessors(chain = true)
public class TableInfo {


	/**
	 * 实体类型
	 */
	private Class<?> entityType;


	/**
	 * 表主键ID 类型
	 */
	private IdType idType = IdType.AUTO;

	/**
	 * 表主键ID 字段名
	 */
	private String keyJdbcColumn;
	/**
	 * 表主键ID 属性名
	 */
	private String keyJavaColumn;

	/**
	 * 表主键ID 属性类型
	 */
	private Class<?> keyType;

	/**
	 * 备注
	 *
	 * @since 2022/11/27
	 */

	private String remark;

	private List<TableFieldInfo> tableFieldInfos;

	/**
	 * 表名
	 *
	 * @since 2022/11/28
	 */

	private String tableName;
}

