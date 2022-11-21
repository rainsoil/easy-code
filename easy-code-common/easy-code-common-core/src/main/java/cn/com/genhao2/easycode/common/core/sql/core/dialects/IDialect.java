package cn.com.genhao2.easycode.common.core.sql.core.dialects;

/**
 * 数据库 分页语句组装接口
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public interface IDialect {


	/**
	 * 组装分页语句
	 *
	 * @param originalSql 原始语句
	 * @param offset      偏移量
	 * @param limit       界限
	 * @return java.lang.String
	 * @since 2022/11/20
	 */
	String buildPaginationSql(String originalSql, long offset, long limit);

}
