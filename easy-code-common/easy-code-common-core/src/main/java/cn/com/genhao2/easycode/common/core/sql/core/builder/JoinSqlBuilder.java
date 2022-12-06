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
	 * @param condition   触发条件
	 * @param table       标
	 * @param leftColumn  左边的条件
	 * @param rightColumn 右边的条件
	 * @return Children
	 * @since 2022/11/21
	 */
	Children leftJoin(boolean condition, String table, R leftColumn, R rightColumn);

	/**
	 * left join
	 *
	 * @param condition
	 * @param table
	 * @param onSql
	 * @return Children
	 * @since 2022/12/1
	 */
	Children leftJoin(boolean condition, String table, String onSql);


	/**
	 * left join
	 *
	 * @param table
	 * @param onSql
	 * @return Children
	 * @since 2022/12/1
	 */
	default Children leftJoin(String table, String onSql) {
		return leftJoin(true, table, onSql);
	}

	/**
	 * left join
	 *
	 * @param table       标
	 * @param leftColumn  左边的条件
	 * @param rightColumn 右边的条件
	 * @return Children
	 * @since 2022/11/21
	 */
	default Children leftJoin(String table, R leftColumn, R rightColumn) {
		return leftJoin(true, table, leftColumn, rightColumn);
	}


	/**
	 * right join
	 *
	 * @param condition   触发条件
	 * @param table       标
	 * @param leftColumn  左边的条件
	 * @param rightColumn 右边的条件
	 * @return Children
	 * @since 2022/11/21
	 */
	Children rightJoin(boolean condition, String table, R leftColumn, R rightColumn);


	/**
	 * right join
	 *
	 * @param table       标
	 * @param leftColumn  左边的条件
	 * @param rightColumn 右边的条件
	 * @return Children
	 * @since 2022/11/21
	 */
	default Children rightJoin(String table, R leftColumn, R rightColumn) {
		return rightJoin(true, table, leftColumn, rightColumn);
	}

	/**
	 * right join
	 *
	 * @param condition 触发条件
	 * @param table     标
	 * @return Children
	 * @since 2022/11/21
	 */
	Children rightJoin(boolean condition, String table, String onSql);

	/**
	 * right join
	 *
	 * @param table 标
	 * @return Children
	 * @since 2022/11/21
	 */
	default Children rightJoin(String table, String onSql) {
		return rightJoin(true, table, onSql);
	}
}
