package cn.com.genhao2.easycode.common.core.sql.core.builder;

import cn.com.genhao2.easycode.common.core.sql.core.*;
import cn.com.genhao2.easycode.common.core.sql.core.segments.MergeSegments;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 查询条件封装
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public abstract class AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> extends Wrapper<T> implements
		CompareSqlBuilder<Children, R>,
		JoinSqlBuilder<Children, R>,
		FuncSqlBuilder<Children, R>,
		OrderBySqlBuilder<Children, R>,
		TableNameBuilder<Children, T>,
		GroupSqlBuilder<Children, R> {

	/**
	 * 占位符
	 */
	protected final Children typedThis = (Children) this;
	/**
	 * 补充的sql
	 *
	 * @since 2022/12/1
	 */

	protected String applySql = "";

	protected final List<Object> applySqlParams = new ArrayList<>();
	/**
	 * 表名
	 *
	 * @since 2022/11/21
	 */

	protected String tableName;

	/**
	 * 表名别名
	 *
	 * @since 2022/11/21
	 */

	protected String tableNameAlias;
	private MergeSegments mergeSegments = new MergeSegments();

	@Override
	public String getSqlSegment() {

		return mergeSegments.getSqlSegment();
	}

	@Override
	public List<Object> getSqlSegmentParam() {
		return mergeSegments.getSqlSegmentParam();
	}


	@Override
	public String getTableName() {
		return this.tableName;
	}

	@Override
	public Children eq(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.EQ, val);
	}

	/**
	 * map 所有非空属性等于 =
	 *
	 * @param condition   执行条件
	 * @param params      map 类型的参数, key 是字段名, value 是字段值
	 * @param null2IsNull 是否参数为 null 自动执行 isNull 方法, false 则忽略这个字段\
	 * @return children
	 */
	@Override
	public <V> Children allEq(boolean condition, Map<R, V> params, boolean null2IsNull) {
		if (condition && CollectionUtil.isNotEmpty(params)) {
			params.forEach((k, v) -> {
				if (ObjectUtil.isNotNull(v)) {
					eq(k, v);
				} else {
					if (null2IsNull) {
						isNull(k);
					}
				}
			});
		}
		return typedThis;
	}

	/**
	 * 字段过滤接口，传入多参数时允许对参数进行过滤
	 *
	 * @param condition   执行条件
	 * @param filter      返回 true 来允许字段传入比对条件中
	 * @param params      map 类型的参数, key 是字段名, value 是字段值
	 * @param null2IsNull 是否参数为 null 自动执行 isNull 方法, false 则忽略这个字段
	 * @return children
	 */
	@Override
	public <V> Children allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull) {
		if (condition && CollectionUtil.isNotEmpty(params)) {
			params.forEach((k, v) -> {
				if (filter.test(k, v)) {
					if (ObjectUtil.isNotNull(v)) {
						eq(k, v);
					} else {
						if (null2IsNull) {
							isNull(k);
						}
					}
				}
			});
		}
		return typedThis;
	}

	/**
	 * 不等于 &lt;&gt;
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children ne(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.NE, val);
	}

	/**
	 * 大于 &gt;
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children gt(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.GT, val);
	}

	/**
	 * 大于等于 &gt;=
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children ge(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.GE, val);
	}

	/**
	 * 小于 &lt;
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children lt(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.LT, val);
	}

	/**
	 * 小于等于 &lt;=
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children le(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.LE, val);
	}

	/**
	 * BETWEEN 值1 AND 值2
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val1      值1
	 * @param val2      值2
	 * @return children
	 */
	@Override
	public Children between(boolean condition, R column, Object val1, Object val2) {
		return addCondition(condition, column, SqlKeyword.BETWEEN, val1, val2);
	}

	/**
	 * NOT BETWEEN 值1 AND 值2
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val1      值1
	 * @param val2      值2
	 * @return children
	 */
	@Override
	public Children notBetween(boolean condition, R column, Object val1, Object val2) {
		return addCondition(condition, column, SqlKeyword.NOT_BETWEEN, val1, val2);
	}

	/**
	 * LIKE '%值%'
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children like(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.LIKE, val);
	}

	/**
	 * NOT LIKE '%值%'
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children notLike(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.NOT_LIKE, val);
	}

	/**
	 * LIKE '%值'
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children likeLeft(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.LIKE_LEFT, val);
	}

	/**
	 * LIKE '值%'
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @param val       值
	 * @return children
	 */
	@Override
	public Children likeRight(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.LIKE_RIGHT, val);
	}

	@Override
	public Children isNull(boolean condition, R column) {
		return maybeDo(condition, () -> {
			mergeSegments.add(SqlKeyword.IS_NULL, columnToString(column));
		});
	}


	/**
	 * 字段 IS NOT NULL
	 * <p>例: isNotNull("name")</p>
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @return children
	 */
	@Override
	public Children isNotNull(boolean condition, R column) {
		return addCondition(condition, column, SqlKeyword.IS_NOT_NULL);
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
	@Override
	public Children in(boolean condition, R column, Collection<?> coll) {
		return in(condition, column, coll.toArray());
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
	@Override
	public Children in(boolean condition, R column, Object... values) {
		return addCondition(condition, column, SqlKeyword.IN, Arrays.stream(values).map(a -> a.toString()).collect(Collectors.joining(",")));
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
	@Override
	public Children notIn(boolean condition, R column, Collection<?> coll) {
		return notIn(condition, column, coll.toArray());
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
	@Override
	public Children notIn(boolean condition, R column, Object... values) {
		return addCondition(condition, column, SqlKeyword.NOT_IN, Arrays.stream(values).map(a -> a.toString()).collect(Collectors.joining(",")));
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
	@Override
	public Children inSql(boolean condition, R column, String inValue) {
		return addCondition(condition, column, SqlKeyword.IN_STR, inValue);
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
	@Override
	public Children notInSql(boolean condition, R column, String inValue) {
		return addCondition(condition, column, SqlKeyword.NOT_IN_STR, inValue);
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
	@Override
	public Children rightJoin(boolean condition, String table, R leftColumn, R rightColumn) {
		return maybeDo(condition, () -> {
			mergeSegments.add(SqlKeyword.RIGHT_JOIN, table, leftColumn, rightColumn);
		});
	}

	@Override
	public Children from(Class<T> tableNameClazz) {
		TableInfo tableInfo = TableInfoHelper.getTableInfo(tableNameClazz, NamingStrategy.UNDERLINE_TO_CAMEL);
		this.tableName = tableInfo.getTableName();
		return typedThis;
	}

	@Override
	public Children from(String tableName, String alias) {
		this.tableName = tableName;
		this.tableNameAlias = alias;
		return typedThis;
	}


	/**
	 * 添加的sql
	 *
	 * @param condition
	 * @param applySql
	 * @param values
	 * @return Children
	 * @since 2022/12/1
	 */
	@Override
	public Children apply(boolean condition, String applySql, Object... values) {

		if (condition) {
			this.applySql = this.applySql + " " + applySql;
			for (Object value : values) {
				applySqlParams.add(value);
			}
		}
		return typedThis;
	}

	/**
	 * left join
	 *
	 * @param condition
	 * @param table
	 * @param onSql
	 * @return Children
	 * @since 2022/12/1
	 */
	@Override
	public Children leftJoin(boolean condition, String table, String onSql) {
		return maybeDo(condition, () -> {
			mergeSegments.add(SqlKeyword.LEFT_JOIN, table, onSql);
		});
	}

	/**
	 * right join
	 *
	 * @param condition 触发条件
	 * @param table     标
	 * @param onSql
	 * @return Children
	 * @since 2022/11/21
	 */
	@Override
	public Children rightJoin(boolean condition, String table, String onSql) {
		return maybeDo(condition, () -> {
			mergeSegments.add(SqlKeyword.RIGHT_JOIN, table, onSql);
		});
	}

	/**
	 * ascasc 排序
	 *
	 * @param condition 执行条件
	 * @param column    字段
	 * @return Children
	 * @since 2022/11/21
	 */
	@Override
	public Children orderByAsc(boolean condition, R column) {
		return addCondition(condition, column, SqlKeyword.ASC);
	}

	/**
	 * 构造查询条件
	 *
	 * @param condition  是否执行
	 * @param column     字段
	 * @param sqlKeyword 关键字
	 * @param val        值
	 * @return Children
	 * @since 2022/11/20
	 */
	protected Children addCondition(boolean condition, R column, SqlKeyword sqlKeyword, Object... val) {
		return maybeDo(condition, () -> {
			mergeSegments.add(sqlKeyword, columnToString(column), val);
		});
	}

	/**
	 * 获取 columnName
	 */
	protected String columnToString(R column) {
		return (String) column;
	}


	@Override
	public Children or(boolean condition, Supplier<Children> supplier) {
		if (condition) {
			Children children = supplier.get();
			mergeSegments.add(SqlKeyword.OR, children.getSqlSegment(), children.getSqlSegmentParam().toArray());
		}
		return typedThis;
	}

	@Override
	public Children orderByDesc(boolean condition, R column) {
		return maybeDo(condition, () -> {
			mergeSegments.add(SqlKeyword.DESC, columnToString(column));
		});
	}

	@Override
	public Children leftJoin(boolean condition, String table, R leftColumn, R rightColumn) {
		return maybeDo(condition, () -> {
			mergeSegments.add(SqlKeyword.LEFT_JOIN, table, leftColumn, rightColumn);
		});
	}

	@Override
	public Children groupBy(boolean condition, R... columns) {
		return maybeDo(condition, () -> {
			for (R column : columns) {

				mergeSegments.add(SqlKeyword.GROUP_BY, columnToString(column));
			}
		});
	}

	@Override
	public Children having(boolean condition, R column, Object val) {
		return maybeDo(condition, () -> {
			mergeSegments.add(SqlKeyword.HAVING, columnToString(column), val);
		});
	}

	/**
	 * 子类返回一个自己的新对象
	 */
	protected abstract Children instance();

	/**
	 * 获取sql
	 *
	 * @return Children
	 * @since 2022/11/21
	 */
	public abstract String getSql();

	/**
	 * 函数化的做事
	 *
	 * @param condition 做不做
	 * @param something 做什么
	 * @return Children
	 */
	protected final Children maybeDo(boolean condition, DoSomething something) {
		if (condition) {
			something.doIt();
		}
		return typedThis;
	}

	/**
	 * 做事函数
	 */
	@FunctionalInterface
	public interface DoSomething {


		/**
		 * do  some thing
		 *
		 * @since 2022/11/21
		 */
		void doIt();
	}
}
