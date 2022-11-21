package cn.com.genhao2.easycode.common.core.spi.annotation;

import java.lang.annotation.*;

/**
 * SPI注解,标注在接口类上,表明是一个SPI接口
 *
 * @author luyanan
 * @since 2022/11/19
 **/
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface SPI {


	/**
	 * 默认扩展点的名称
	 *
	 * @return java.lang.String
	 * @since 2022/11/19
	 */
	String value();


}
