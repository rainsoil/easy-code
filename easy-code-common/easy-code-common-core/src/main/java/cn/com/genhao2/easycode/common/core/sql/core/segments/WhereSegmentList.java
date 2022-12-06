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
				SqlKeyword.EQ,
				SqlKeyword.OR,
				SqlKeyword.IS_NULL,
				SqlKeyword.IN,
				SqlKeyword.IN_STR,
				SqlKeyword.NOT_IN_STR,
				SqlKeyword.NOT_IN,
				SqlKeyword.LIKE,
				SqlKeyword.LIKE_LEFT,
				SqlKeyword.LIKE_RIGHT,
				SqlKeyword.NOT_LIKE,
				SqlKeyword.EQ,
				SqlKeyword.NE,
				SqlKeyword.GT,
				SqlKeyword.LT,
				SqlKeyword.LE,
				SqlKeyword.IS_NOT_NULL,
				SqlKeyword.EXISTS,
				SqlKeyword.NOT_EXISTS,
				SqlKeyword.BETWEEN,
				SqlKeyword.NOT_BETWEEN
		};
	}

	@Override
	public void add(SqlKeyword sqlKeyword, String column, Object... val) {


		if (sqlKeyword.equals(SqlKeyword.OR)) {
			for (Object o : val) {
				params.add(o);
			}
			String sql = StrUtil.format(sqlKeyword.getSqlSegment(), column);
			orSql.add(sql);
		} else if (sqlKeyword.equals(SqlKeyword.IN_STR) || sqlKeyword.equals(SqlKeyword.NOT_IN_STR)) {

			andSql.add(StrUtil.format(sqlKeyword.getSqlSegment(), column, val[0]));
		} else {
			for (Object o : val) {
				params.add(o);
			}
			andSql.add(StrUtil.format(sqlKeyword.getSqlSegment(), column));

		}
	}


}
