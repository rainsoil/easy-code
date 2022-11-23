package cn.com.genhao2.easycode.common.core.sql.core;

import cn.com.genhao2.easycode.common.core.sql.core.builder.AbstractWrapper;
import cn.com.genhao2.easycode.common.core.sql.core.builder.Query;
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
		return SqlKeyword.SELECT.getSqlSegment() + " " + selectField.stream().collect(Collectors.joining(",")) + SqlKeyword.FROM.getSqlSegment() + tableName +
				Opt.ofBlankAble(tableNameAlias).map(a -> " AS " + tableNameAlias).orElse(" ") + super.getSqlSegment();
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


}
