package cn.com.genhao2.easycode.common.core.sql.core;

import cn.com.genhao2.easycode.common.core.sql.core.builder.AbstractWrapper;
import cn.com.genhao2.easycode.common.core.sql.core.builder.Query;

/**
 * 查询构造器
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public class QueryWrapper<T> extends AbstractWrapper<T, String, QueryWrapper<T>>
		implements Query<QueryWrapper<T>, T, String> {


	@Override
	public QueryWrapper<T> select(String... columns) {
		return null;
	}

	@Override
	public String getSqlSelect() {
		return null;
	}

	@Override
	protected QueryWrapper<T> instance() {
		return new QueryWrapper<>();
	}
}
