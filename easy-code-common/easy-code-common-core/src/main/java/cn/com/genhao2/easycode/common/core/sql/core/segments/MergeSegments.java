package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.ISqlSegment;
import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码合并片段
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public class MergeSegments implements ISqlSegment {

	Map<SqlKeyword, AbstractSegmentList> map = new HashMap<>();
	List<Object> sqlSegmentParams = new ArrayList<>();
	WhereSegmentList where = new WhereSegmentList();


	@Override
	public String getSqlSegment() {
		return where.getSqlSegment();
	}

	@Override
	public List<Object> getSqlSegmentParam() {
		sqlSegmentParams.addAll(where.getSqlSegmentParam());
		return sqlSegmentParams;
	}


	// 普通片段
	//  left join 片段
	// group片段
	// having 片段
	// 排序片段

	public void add(SqlKeyword sqlKeyword, String column, Object... val) {
		for (SqlKeyword keyword : where.supportSqlKeyword()) {
			map.put(keyword, where);
		}
		AbstractSegmentList segmentList = map.get(sqlKeyword);
		segmentList.add(sqlKeyword, column, val);
	}


}
