package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.ISqlSegment;
import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;
import cn.hutool.core.lang.Opt;
import lombok.Getter;

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
@Getter
public class MergeSegments implements ISqlSegment {

	Map<SqlKeyword, AbstractSegmentList> map = new HashMap<>();
	List<Object> sqlSegmentParams = new ArrayList<>();
	WhereSegmentList where = new WhereSegmentList();
	OrderBySegmentList orderby = new OrderBySegmentList();
	JoinSegmentList join = new JoinSegmentList();
	GroupBySegmentList group = new GroupBySegmentList();

	@Override
	public String getSqlSegment() {

		return join.getSqlSegment() +
				Opt.ofBlankAble(where.getSqlSegment())
						.map(a -> SqlKeyword.WHERE.getSqlSegment()
								+ where.getSqlSegment()).orElse("")
				+
				Opt.ofBlankAble(group.getSqlSegment()).map(a -> SqlKeyword.GROUP_BY.getSqlSegment() + " " + group.getSqlSegment()).orElse(" ")

				+ " " + orderby.getSqlSegment();
	}

	@Override
	public List<Object> getSqlSegmentParam() {
		sqlSegmentParams.addAll(where.getSqlSegmentParam());
		sqlSegmentParams.addAll(group.getSqlSegmentParam());
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
		for (SqlKeyword keyword : orderby.supportSqlKeyword()) {
			map.put(keyword, orderby);
		}
		for (SqlKeyword keyword : join.supportSqlKeyword()) {
			map.put(keyword, join);
		}
		for (SqlKeyword keyword : group.supportSqlKeyword()) {
			map.put(keyword, group);
		}
		AbstractSegmentList segmentList = map.get(sqlKeyword);
		segmentList.add(sqlKeyword, column, val);
	}


}
