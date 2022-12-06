package cn.com.genhao2.easycode.common.core.sql.core.annotation;

import java.lang.annotation.*;

/**
 * 数据库表名注解
 *
 * @author luyanan
 * @since 2022/11/27
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Documented
public @interface TableName {

	/**
	 * 实体对应的表名
	 *
	 * @since 2022/11/27
	 */
	String value() default "";
}
