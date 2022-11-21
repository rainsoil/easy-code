package cn.com.genhao2.easycode.common.core.sql.core.builder;

import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;
import cn.com.genhao2.easycode.common.core.sql.core.Wrapper;
import cn.com.genhao2.easycode.common.core.sql.core.segments.MergeSegments;

import java.util.List;
import java.util.function.Supplier;

/**
 * 查询条件封装
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public abstract class AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> extends Wrapper<T> implements CompareSqlBuilder<Children, R>, JoinSqlBuilder<Children>, FuncSqlBuilder<Children, R> {

	/**
	 * 占位符
	 */
	protected final Children typedThis = (Children) this;
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
		return null;
	}

	@Override
	public Children eq(boolean condition, R column, Object val) {
		return addCondition(condition, column, SqlKeyword.EQ, val);
	}

	@Override
	public Children isNull(boolean condition, R column) {
		return null;
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

		if (condition) {
			mergeSegments.add(sqlKeyword, columnToString(column), val);
		}
		return typedThis;
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


	/**
	 * 子类返回一个自己的新对象
	 */
	protected abstract Children instance();

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

		void doIt();
	}
}
