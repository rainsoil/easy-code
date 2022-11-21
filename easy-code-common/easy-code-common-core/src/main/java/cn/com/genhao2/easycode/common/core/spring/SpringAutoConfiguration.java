package cn.com.genhao2.easycode.common.core.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * spirng的配置类
 *
 * @author luyanan
 * @since 2022/11/8
 **/
@Configuration
@Import(SpringContextHolder.class)
public class SpringAutoConfiguration {
}
