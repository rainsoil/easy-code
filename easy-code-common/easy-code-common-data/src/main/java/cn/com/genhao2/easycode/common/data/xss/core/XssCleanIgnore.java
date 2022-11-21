package cn.com.genhao2.easycode.common.data.xss.core;

import java.lang.annotation.*;

/**
 * xss 忽略注解
 *
 * @author luyanan
 * @since 2022/7/4
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XssCleanIgnore {
}