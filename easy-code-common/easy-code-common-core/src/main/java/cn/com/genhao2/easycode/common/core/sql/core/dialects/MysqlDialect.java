package cn.com.genhao2.easycode.common.core.sql.core.dialects;

/**
 * mysql 分页组装
 *
 * @author luyanan
 * @since 2022/12/1
 **/
public class MysqlDialect implements IDialect {
	/**
	 * 组装分页语句
	 *
	 * @param originalSql 原始语句
	 * @param offset      偏移量
	 * @param limit       界限
	 * @return java.lang.String
	 * @since 2022/11/20
	 */
	@Override
	public String buildPaginationSql(String originalSql, long offset, long limit) {
		return originalSql + "  limit " + offset + "," + limit;
	}
}
