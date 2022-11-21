package cn.com.genhao2.easycode.common.core.spi.annotation;

import java.lang.annotation.*;

/**
 * 依赖注入
 *
 * @author luyanan
 * @since 2022/11/19
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {

	/**
	 * <p>被注入扩展点的名称, 默认情况下注入默认扩展点</p>
	 *
	 * @return {@link String}
	 * @author luyanan
	 * @since 2020/1/2
	 */
	String value() default "";
}
