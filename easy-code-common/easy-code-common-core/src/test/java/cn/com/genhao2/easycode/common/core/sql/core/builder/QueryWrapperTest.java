package cn.com.genhao2.easycode.common.core.sql.core.builder;

import cn.com.genhao2.easycode.common.core.sql.core.QueryWrapper;
import org.junit.Test;

public class QueryWrapperTest {


	@Test
	public void select() {

		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq(true, "name", "11")
				.eq(true, "age", 15).or(true, () -> {
			QueryWrapper<Object> sex = new QueryWrapper<>()
					.eq(true, "or-sex", 1).eq(true, "or-age", "1111");
			return sex;
		});
		System.out.println(queryWrapper.getSqlSegment());
		System.out.println("sql参数:" + queryWrapper.getSqlSegmentParam());

	}

}