package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;

/**
 * order
 *
 * @author luyanan
 * @since 2022/11/21
 **/
public interface OrderBySqlBuilder<Children, R> extends Serializable {
	/**
	 * desc 排序
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @return Children
	 * @since 2022/11/21
	 */
	Children orderByDesc(boolean condition, R column);


	/**
	 * desc 排序
	 *
	 * @param column 字段
	 * @return Children
	 * @since 2022/11/21
	 */
	default Children orderByDesc(R column) {
		return orderByDesc(true, column);
	}


	/**
	 * ascasc 排序
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @return Children
	 * @since 2022/11/21
	 */
	Children orderByAsc(boolean condition, R column);


	/**
	 * asc 排序
	 *
	 * @param column 字段
	 * @return Children
	 * @since 2022/11/21
	 */
	default Children orderByAsc(R column) {
		return orderByAsc(true, column);
	}
}
