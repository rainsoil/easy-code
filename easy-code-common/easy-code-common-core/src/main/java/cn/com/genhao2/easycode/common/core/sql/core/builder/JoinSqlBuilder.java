package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 拼接
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public interface JoinSqlBuilder<Children, R> extends Serializable {


	/**
	 * left join
	 *
	 * @param condition
	 * @param table
	 * @param leftColumn
	 * @param rightColumn
	 * @return Children
	 * @since 2022/11/21
	 */
	Children leftJoin(boolean condition, String table, R leftColumn, R rightColumn);
}
