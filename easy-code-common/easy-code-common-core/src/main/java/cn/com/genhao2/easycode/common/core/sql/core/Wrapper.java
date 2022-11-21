package cn.com.genhao2.easycode.common.core.sql.core;

/**
 * 条件构造抽象类
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public abstract class Wrapper<T> implements ISqlSegment {


	/**
	 * 获取表名
	 *
	 * @return java.lang.String
	 * @since 2022/11/19
	 */
	public abstract String getTableName();


	/**
	 * 获取格式化后的执行sql
	 *
	 * @return java.lang.String
	 * @since 2022/11/19
	 */
	public String getTargetSql() {
		return getSqlSegment().replaceAll("#\\{.+?}", "?");
	}
}
