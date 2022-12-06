package cn.com.genhao2.easycode.common.core.sql.core.Lambda;

import cn.com.genhao2.easycode.common.core.sql.core.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LambdaQueryWrapperTest {


	@Test
	public void testQuery() {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
				.from(User.class);
//				.eq(true, User::getId, 1);
//				.select(User::getName);
		System.out.println(lambdaQueryWrapper.getSql());
	}
}