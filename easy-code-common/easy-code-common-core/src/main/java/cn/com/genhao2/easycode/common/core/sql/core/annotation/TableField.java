package cn.com.genhao2.easycode.common.core.sql.core.annotation;

import cn.com.genhao2.easycode.common.core.sql.core.SqlCondition;

import java.lang.annotation.*;

/**
 * 数据库字段注解配置
 *
 * @author luyanan
 * @since 2022/11/28
 **/
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableField {


	/**
	 * 数据库字段值
	 *
	 * @since 2022/11/28
	 */

	String value() default "";


	/**
	 * 是否为数据库表字段
	 *
	 * @since 2022/11/28
	 */

	boolean exist() default true;

	/**
	 * 字段 where 实体查询比较条件
	 * <p>
	 * 默认 {@link SqlCondition#EQUAL}
	 */
	String condition() default "";
}
