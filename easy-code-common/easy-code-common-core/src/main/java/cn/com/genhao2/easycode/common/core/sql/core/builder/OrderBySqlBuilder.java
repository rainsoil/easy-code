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

}
