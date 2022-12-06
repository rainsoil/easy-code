package cn.com.genhao2.easycode.common.core.sql.core.annotation;

import cn.com.genhao2.easycode.common.core.sql.core.enums.IdType;

import java.lang.annotation.*;

/**
 * 表主键标识
 *
 * @author luyanan
 * @since 2022/11/28
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface TableId {


	/**
	 * 字段名（该值可无）
	 */
	String value() default "";

	/**
	 * 主键类型
	 * {@link IdType}
	 */
	IdType type() default IdType.NONE;
}
