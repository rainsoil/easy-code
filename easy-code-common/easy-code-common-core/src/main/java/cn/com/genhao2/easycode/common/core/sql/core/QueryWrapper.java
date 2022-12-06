package cn.com.genhao2.easycode.common.core.sql.core;

import cn.com.genhao2.easycode.common.core.sql.core.builder.AbstractWrapper;
import cn.com.genhao2.easycode.common.core.sql.core.builder.Query;
import cn.com.genhao2.easycode.common.core.sql.core.dialects.DialectEngine;
import cn.com.genhao2.easycode.common.core.sql.core.dialects.IDialect;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询构造器
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public class QueryWrapper<T> extends AbstractWrapper<T, String, QueryWrapper<T>>
		implements Query<QueryWrapper<T>, T, String> {

	protected List<String> selectField = new ArrayList<>();
	private Long offset;
	private Long limit;

	@Override
	protected QueryWrapper<T> instance() {
		return new QueryWrapper<>();
	}


	/**
	 * 获取sql
	 *
	 * @return Children
	 * @since 2022/11/21
	 */
	@Override
	public String getSql() {
		if (CollectionUtil.isEmpty(selectField)) {
			selectField.add("*");
		}
		String sql = SqlKeyword.SELECT.getSqlSegment() + " " + selectField.stream().collect(Collectors.joining(",")) + SqlKeyword.FROM.getSqlSegment() + tableName +
				Opt.ofBlankAble(tableNameAlias).map(a -> " AS " + tableNameAlias).orElse(" ") + super.getSqlSegment() + applySql;
		if (null == offset || null == limit) {
			return sql;
		} else {

			IDialect dialect = DialectEngine.getDialect();
			return dialect.buildPaginationSql(sql, offset, limit);
		}
	}


	@Override
	public List<Object> getSqlSegmentParam() {
		List<Object> sqlSegmentParam = super.getSqlSegmentParam();
		sqlSegmentParam.addAll(applySqlParams);
		return sqlSegmentParam;
	}

	/**
	 * ignore
	 *
	 * @param columns
	 */
	@Override
	public QueryWrapper<T> select(String... columns) {
		for (String column : columns) {
			selectField.add(column);
		}
		return typedThis;
	}

	/**
	 * 分页
	 *
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return Children
	 * @since 2022/12/1
	 */
	@Override
	public QueryWrapper<T> limit(boolean condition, long offset, long limit) {
		if (condition) {

			this.offset = offset;
			this.limit = limit;
		}

		return typedThis;
	}


}
