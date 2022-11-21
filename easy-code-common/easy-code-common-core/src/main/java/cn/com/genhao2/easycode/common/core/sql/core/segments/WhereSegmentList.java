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

		return andSql.stream().collect(Collectors.joining(SqlKeyword.AND.getSqlSegment())) + orSql.stream().collect(Collectors.joining(" "));
	}

	@Override
	public List<Object> getSqlSegmentParam() {
		return params;
	}

	@Override
	protected SqlKeyword[] supportSqlKeyword() {
		return new SqlKeyword[]{
				SqlKeyword.EQ, SqlKeyword.OR
		};
	}

	@Override
	public void add(SqlKeyword sqlKeyword, String column, Object... val) {


		if (sqlKeyword.equals(SqlKeyword.OR)) {

			String sql = StrUtil.format(sqlKeyword.getSqlSegment(), column);
			orSql.add(sql);
			for (Object o : val) {
				params.add(o);
			}
		}
		if (sqlKeyword.equals(SqlKeyword.EQ)) {

			String sqlSegment = sqlKeyword.getSqlSegment();
			andSql.add(StrUtil.format(sqlKeyword.getSqlSegment(), column));
			params.add(val[0]);
		}

	}


}
