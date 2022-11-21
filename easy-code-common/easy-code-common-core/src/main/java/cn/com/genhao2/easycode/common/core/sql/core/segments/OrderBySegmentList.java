package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;

import java.util.List;

/**
 * 排序片段
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public class OrderBySegmentList extends AbstractSegmentList {
	@Override
	public String getSqlSegment() {
		return null;
	}

	@Override
	public List<Object> getSqlSegmentParam() {
		return null;
	}

	@Override
	protected SqlKeyword[] supportSqlKeyword() {
		return new SqlKeyword[]{
				SqlKeyword.ORDER_BY
		};
	}

	@Override
	public void add(SqlKeyword sqlKeyword, String column, Object... val) {

	}
}
