package cn.com.genhao2.easycode.common.core.sql.core.builder;

import cn.com.genhao2.easycode.common.core.sql.core.QueryWrapper;
import cn.com.genhao2.easycode.common.core.sql.core.User;
import cn.com.genhao2.easycode.common.core.utils.JsonUtils;
import org.junit.Test;

public class QueryWrapperTest {


	@Test
	public void select() {

		QueryWrapper<User> queryWrapper = new QueryWrapper();
		queryWrapper
				.select("name", "age", "sex")
//				.eq(true, "name", "11")
//				.eq(true, "age", 15)
//				.or(true, () -> {
//					QueryWrapper<User> sex = new QueryWrapper<User>()
//							.eq(true, "or-sex", 1).eq(true, "or-age", "1111");
//					return sex;
//				})
//
//
				.between("id", 1, 2)
//
				.in("id", "1", "2", "34")
				.inSql("id", "select * from user")
//				.like("name", "张三")
//				.orderByDesc(true, "create_time")
//				.orderByDesc(true, "id")
//
//
				.leftJoin(true, "role", "user_id", "user_id")
				.leftJoin("sys_dict", "sys_dict.user_id = sys_user.id ")
				.rightJoin(true, "role", "user_id", "user_id")
				.rightJoin("sys_dict", "sys_dict.user_id = sys_user.id ")
//				.groupBy(true, "age", "name").having(true, "id", "11")
//				.isNull(true, "id");
				.from(" user", " u ")
				.limit(1, 11)
		.apply("  and  tenant_id = ?",11111);

		String sql = queryWrapper.getSql();
		System.out.println(queryWrapper.getSql());
		System.out.println("sql参数:" + JsonUtils.toJson(queryWrapper.getSqlSegmentParam()));

	}


}