package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;
import cn.hutool.core.lang.Opt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * group 片段
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public class GroupBySegmentList extends AbstractSegmentList {

	private List<String> fields = new ArrayList<>();

	private List<String> having = new ArrayList<>();
	private List<Object> params = new ArrayList<>();

	/**
	 * sql片段
	 *
	 * @return java.lang.String
	 * @since 2022/11/19
	 */
	@Override
	public String getSqlSegment() {
		return fields.stream().collect(Collectors.joining(",")) + " " +
				Opt.ofEmptyAble(having).map(a ->
						SqlKeyword.HAVING.getSqlSegment() + " " +


								a.stream().map(b -> b + " = ? ").collect(Collectors.joining(","))).orElse("");


	}

	/**
	 * sql参数
	 *
	 * @return java.util.List<java.lang.Object>
	 * @since 2022/11/20
	 */
	@Override
	public List<Object> getSqlSegmentParam() {
		return this.params;
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
				SqlKeyword.GROUP_BY, SqlKeyword.HAVING
		};
	}

	@Override
	public void add(SqlKeyword sqlKeyword, String column, Object... val) {

		if (sqlKeyword.equals(SqlKeyword.GROUP_BY)) {

			fields.add(column);

		}
		if (sqlKeyword.equals(SqlKeyword.HAVING)) {
			having.add(column);
			for (Object o : val) {
				params.add(o);

			}
		}
	}
}
