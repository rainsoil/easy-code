package cn.com.genhao2.easycode.common.core.sql.core.dialects;

import lombok.experimental.UtilityClass;

/**
 * 分页方言引擎
 *
 * @author luyanan
 * @since 2022/12/1
 **/
@UtilityClass
public class DialectEngine {


	public IDialect getDialect() {
		// 这里要根据当前的数据库配置, 默认的选择数据库引擎

		return new MysqlDialect();
	}


}
