package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;

/**
 * 表名构建
 *
 * @author luyanan
 * @since 2022/11/21
 **/
public interface TableNameBuilder<Children> extends Serializable {


	/**
	 * 设置表名
	 *
	 * @param tableName 表名
	 * @param alias     别名
	 * @return Children
	 * @since 2022/11/21
	 */
	Children table(String tableName, String alias);


}
