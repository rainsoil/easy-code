package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;
import lombok.val;

import java.util.List;

/**
 * 方法 片段
 *
 * @author luyanan
 * @since 2022/11/23
 **/
public class FunSegmentList extends AbstractSegmentList {
	/**
	 * sql片段
	 *
	 * @return java.lang.String
	 * @since 2022/11/19
	 */
	@Override
	public String getSqlSegment() {
		return null;
	}

	/**
	 * sql参数
	 *
	 * @return java.util.List<java.lang.Object>
	 * @since 2022/11/20
	 */
	@Override
	public List<Object> getSqlSegmentParam() {
		return null;
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
				SqlKeyword.IS_NULL
		};
	}

	@Override
	public void add(SqlKeyword sqlKeyword, String column, Object... val) {

	}
}
