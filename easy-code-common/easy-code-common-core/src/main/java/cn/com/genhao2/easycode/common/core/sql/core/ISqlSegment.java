package cn.com.genhao2.easycode.common.core.sql.core;

import java.io.Serializable;
import java.util.List;

/**
 * SQL 片段接口
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public interface ISqlSegment extends Serializable {

	/**
	 * sql片段
	 *
	 * @return java.lang.String
	 * @since 2022/11/19
	 */
	String getSqlSegment();


	/**
	 * sql参数
	 *
	 * @return java.util.List<java.lang.Object>
	 * @since 2022/11/20
	 */
	List<Object> getSqlSegmentParam();
}
