package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 普通片段
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public class WhereSegmentList extends AbstractSegmentList {

	List<String> orSql = new ArrayList<>();
	List<String> andSql = new ArrayList<>();
	List<Object> params = new ArrayList<>();


	@Override
	public String getSqlSegment() {
		String andSqls = andSql.stream().collect(Collectors.joining(SqlKeyword.AND.getSqlSegment()));
		String orSqls = orSql.stream().collect(Collectors.joining(" ")).replace(SqlKeyword.WHERE.getSqlSegment(), "");
		return andSqls + orSqls;
	}

	@Override
	public List<Object> getSqlSegmentParam() {
		return params;
	}

	@Override
	protected SqlKeyword[] supportSqlKeyword() {
		return new SqlKeyword[]{
				SqlKeyword.EQ, SqlKeyword.OR, SqlKeyword.IS_NULL
		};
	}

	@Override
	public void add(SqlKeyword sqlKeyword, String column, Object... val) {

//
		for (Object o : val) {
			params.add(o);
		}
		if (sqlKeyword.equals(SqlKeyword.OR)) {

			String sql = StrUtil.format(sqlKeyword.getSqlSegment(), column);
			orSql.add(sql);
		} else {
			andSql.add(StrUtil.format(sqlKeyword.getSqlSegment(), column));
		}
	}


}
