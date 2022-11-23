package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 排序片段
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public class OrderBySegmentList extends AbstractSegmentList {

	private Map<String, SqlKeyword> sql = new HashMap<>();

	@Override
	public String getSqlSegment() {

		if (sql.keySet().size() == 0) {
			return "";
		}
		return SqlKeyword.ORDER_BY+" " + sql.entrySet().stream().map(k -> k.getKey() + " " + k.getValue().getSqlSegment()).collect(Collectors.joining(","));
	}

	@Override
	public List<Object> getSqlSegmentParam() {
		return new ArrayList<>();
	}

	@Override
	protected SqlKeyword[] supportSqlKeyword() {
		return new SqlKeyword[]{
				SqlKeyword.DESC, SqlKeyword.ASC
		};
	}

	@Override
	public void add(SqlKeyword sqlKeyword, String column, Object... val) {
		sql.put(column, sqlKeyword);

	}
}
