package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public interface Query<Children, T, R> extends Serializable {


	/**
	 * ignore
	 */
	@SuppressWarnings("unchecked")
	Children select(R... columns);


	/**
	 * 分页
	 *
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return Children
	 * @since 2022/12/1
	 */
	Children limit(boolean condition, long offset, long limit);

	/**
	 * 分页
	 *
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return Children
	 * @since 2022/12/1
	 */
	default Children limit(long offset, long limit) {
		return limit(true, offset, limit);
	}
}
