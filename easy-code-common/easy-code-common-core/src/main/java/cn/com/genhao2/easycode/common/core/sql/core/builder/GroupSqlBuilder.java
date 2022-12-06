package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;

/**
 * 分组构建
 *
 * @author luyanan
 * @since 2022/11/22
 **/
public interface GroupSqlBuilder<Children, R> extends Serializable {


	/**
	 * 分组
	 *
	 * @param condition 触发条件
	 * @param column    字段
	 * @return Children
	 * @since 2022/11/22
	 */
	Children groupBy(boolean condition, R... column);

	/**
	 * 分组
	 *
	 * @param column 字段
	 * @return Children
	 * @since 2022/11/22
	 */
	default Children groupBy(R... column) {
		return groupBy(true, column);
	}

	/**
	 * having
	 *
	 * @param condition
	 * @param column
	 * @param val
	 * @return Children
	 * @since 2022/11/22
	 */
	Children having(boolean condition, R column, Object val);


	/**
	 * having
	 *
	 * @param column
	 * @param val
	 * @return Children
	 * @since 2022/11/22
	 */
	default Children having(R column, Object val) {
		return having(true, column, val);
	}
}
