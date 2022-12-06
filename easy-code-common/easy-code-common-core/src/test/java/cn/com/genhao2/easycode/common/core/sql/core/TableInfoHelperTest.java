package cn.com.genhao2.easycode.common.core.sql.core;

import cn.com.genhao2.easycode.common.core.utils.JsonUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TableInfoHelperTest {


	@Test
	public void getTableInfo() {

		TableInfo tableInfo = TableInfoHelper.getTableInfo(User.class, NamingStrategy.UNDERLINE_TO_CAMEL);
		System.out.println(tableInfo);
	}
}