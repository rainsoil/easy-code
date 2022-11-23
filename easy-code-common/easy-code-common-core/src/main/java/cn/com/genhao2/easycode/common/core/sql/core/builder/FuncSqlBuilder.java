package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 函数构建
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public interface FuncSqlBuilder<Children, R> extends Serializable {
	/**
	 * 拼接or条件
	 *
	 * @param condition
	 * @param supplier
	 * @return Children
	 * @since 2022/11/21
	 */
	Children or(boolean condition, Supplier<Children> supplier);

	/**
	 * 字段 IS NULL
	 * <p>例: isNull("name")</p>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @return children
	 */
	Children isNull(boolean condition, R column);
}
