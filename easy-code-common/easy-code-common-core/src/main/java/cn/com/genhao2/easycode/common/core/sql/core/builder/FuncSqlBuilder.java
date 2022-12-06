package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;
import java.util.Collection;
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


	/**
	 * ignore
	 */
	default Children isNull(R column) {
		return isNull(true, column);
	}


	/**
	 * ignore
	 */
	default Children isNotNull(R column) {
		return isNotNull(true, column);
	}

	/**
	 * 字段 IS NOT NULL
	 * <p>例: isNotNull("name")</p>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @return children
	 */
	Children isNotNull(boolean condition, R column);

	/**
	 * ignore
	 */
	default Children in(R column, Collection<?> coll) {
		return in(true, column, coll);
	}

	/**
	 * 字段 IN (value.get(0), value.get(1), ...)
	 * <p>例: in("id", Arrays.asList(1, 2, 3, 4, 5))</p>
	 *
	 * <li> 注意！集合为空若存在逻辑错误，请在 condition 条件中判断 </li>
	 * <li> 如果集合为 empty 则不会进行 sql 拼接 </li>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param coll      数据集合
	 * @return children
	 */
	Children in(boolean condition, R column, Collection<?> coll);

	/**
	 * ignore
	 */
	default Children in(R column, Object... values) {
		return in(true, column, values);
	}

	/**
	 * 字段 IN (v0, v1, ...)
	 * <p>例: in("id", 1, 2, 3, 4, 5)</p>
	 *
	 * <li> 注意！数组为空若存在逻辑错误，请在 condition 条件中判断 </li>
	 * <li> 如果动态数组为 empty 则不会进行 sql 拼接 </li>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param values    数据数组
	 * @return children
	 */
	Children in(boolean condition, R column, Object... values);

	/**
	 * ignore
	 */
	default Children notIn(R column, Collection<?> coll) {
		return notIn(true, column, coll);
	}

	/**
	 * 字段 NOT IN (value.get(0), value.get(1), ...)
	 * <p>例: notIn("id", Arrays.asList(1, 2, 3, 4, 5))</p>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param coll      数据集合
	 * @return children
	 */
	Children notIn(boolean condition, R column, Collection<?> coll);

	/**
	 * ignore
	 */
	default Children notIn(R column, Object... value) {
		return notIn(true, column, value);
	}

	/**
	 * 字段 NOT IN (v0, v1, ...)
	 * <p>例: notIn("id", 1, 2, 3, 4, 5)</p>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param values    数据数组
	 * @return children
	 */
	Children notIn(boolean condition, R column, Object... values);

	/**
	 * ignore
	 */
	default Children inSql(R column, String inValue) {
		return inSql(true, column, inValue);
	}

	/**
	 * 字段 IN ( sql语句 )
	 * <p>!! sql 注入方式的 in 方法 !!</p>
	 * <p>例1: inSql("id", "1, 2, 3, 4, 5, 6")</p>
	 * <p>例2: inSql("id", "select id from table where id &lt; 3")</p>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param inValue   sql语句
	 * @return children
	 */
	Children inSql(boolean condition, R column, String inValue);


	/**
	 * ignore
	 */
	default Children notInSql(R column, String inValue) {
		return notInSql(true, column, inValue);
	}

	/**
	 * 字段 NOT IN ( sql语句 )
	 * <p>!! sql 注入方式的 not in 方法 !!</p>
	 * <p>例1: notInSql("id", "1, 2, 3, 4, 5, 6")</p>
	 * <p>例2: notInSql("id", "select id from table where id &lt; 3")</p>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param inValue   sql语句 ---&gt; 1,2,3,4,5,6 或者 select id from table where id &lt; 3
	 * @return children
	 */
	Children notInSql(boolean condition, R column, String inValue);


	/**
	 * 添加的sql
	 *
	 * @param condition
	 * @param applySql
	 * @param values
	 * @return Children
	 * @since 2022/12/1
	 */
	Children apply(boolean condition, String applySql, Object... values);

	default Children apply(String applySql, Object... values) {
		return apply(true, applySql, values);
	}

}
