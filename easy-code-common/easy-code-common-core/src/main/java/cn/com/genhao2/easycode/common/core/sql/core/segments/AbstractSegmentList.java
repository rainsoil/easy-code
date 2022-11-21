package cn.com.genhao2.easycode.common.core.sql.core.segments;

import cn.com.genhao2.easycode.common.core.sql.core.ISqlSegment;
import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;

/**
 * 片段抽象类
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public abstract class AbstractSegmentList implements ISqlSegment {


	/**
	 * 支持的关键字
	 *
	 * @return java.lang.String[]
	 * @since 2022/11/20
	 */
	protected abstract SqlKeyword[] supportSqlKeyword();


	public abstract void add(SqlKeyword sqlKeyword, String column, Object... val);
}
