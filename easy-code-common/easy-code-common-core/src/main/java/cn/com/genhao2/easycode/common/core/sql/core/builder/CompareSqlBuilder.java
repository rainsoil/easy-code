package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 查询条件封装
 * <p>比较值</p>
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public interface CompareSqlBuilder<Children, R> extends FuncSqlBuilder<Children, R>, Serializable {



	/**
	 * 等于 =
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	Children eq(boolean condition, R column, Object val);
}
