package cn.com.genhao2.easycode.common.core.sql.core;

import cn.com.genhao2.easycode.common.core.sql.core.annotation.TableField;
import cn.com.genhao2.easycode.common.core.sql.core.annotation.TableId;
import cn.com.genhao2.easycode.common.core.sql.core.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户
 *
 * @author luyanan
 * @since 2022/11/24
 **/
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
public class User {
	@TableId
	private Long id;
	@TableField("user_name")
	private String name;
	private Integer sex;
	@TableField(exist = false)
	private String address;
}
