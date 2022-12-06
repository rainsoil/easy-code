package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * join sql片段处理
 *
 * @author luyanan
 * @since 2022/11/22
 **/
public class JoinSegmentList extends AbstractSegmentList {

	private List<String> sqls = new ArrayList<>();

	/**
	 * sql片段
	 *
	 * @return java.lang.String
	 * @since 2022/11/19
	 */
	@Override
	public String getSqlSegment() {
		return sqls.stream().collect(Collectors.joining(" "));
	}

	/**
	 * sql参数
	 *
	 * @return java.util.List<java.lang.Object>
	 * @since 2022/11/20
	 */
	@Override
	public List<Object> getSqlSegmentParam() {
		return new ArrayList<>();
	}

	/**
	 * 支持的关键字
	 *
	 * @return java.lang.String[]
	 * @since 2022/11/20
	 */
	@Override
	protected SqlKeyword[] supportSqlKeyword() {
		return new SqlKeyword[]{
				SqlKeyword.LEFT_JOIN, SqlKeyword.RIGHT_JOIN
		};
	}

	@Override
	public void add(SqlKeyword sqlKeyword, String column, Object... val) {
		if (val.length == 2) {
			sqls.add(StrUtil.format(sqlKeyword.getSqlSegment(), column, val[0] + " = " + val[1]));
		}
		if (val.length == 1) {
			sqls.add(StrUtil.format(sqlKeyword.getSqlSegment(), column, val[0]));
		}

	}
}
