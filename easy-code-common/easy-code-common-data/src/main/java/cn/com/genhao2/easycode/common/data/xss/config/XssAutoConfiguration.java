package cn.com.genhao2.easycode.common.data.xss.config;

import cn.com.genhao2.easycode.common.data.xss.core.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * xss 自动化配置
 *
 * @author luyanan
 * @since 2022/7/4
 **/
@Configurable
@RequiredArgsConstructor
@EnableConfigurationProperties(XssProperties.class)
@ConditionalOnProperty(
		prefix = XssProperties.PREFIX,
		name = "enabled",
		havingValue = "true",
		matchIfMissing = false
)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class XssAutoConfiguration implements WebMvcConfigurer {
	private final XssProperties xssProperties;


	/**
	 * 默认清理器
	 *
	 * @param properties 配置
	 * @return com.blocks.common.data.xss.core.XssCleaner
	 * @since 2022/7/5
	 */
	@Bean
	@ConditionalOnMissingBean
	public XssCleaner xssCleaner(XssProperties properties) {
		return new DefaultXssCleaner(properties);
	}

	/**
	 * form表单清理器
	 *
	 * @param properties 配置
	 * @param xssCleaner 清理器
	 * @return com.blocks.common.data.xss.core.FormXssClean
	 * @since 2022/7/5
	 */
	@Bean
	public FormXssClean formXssClean(XssProperties properties,
									 XssCleaner xssCleaner) {
		return new FormXssClean(properties, xssCleaner);
	}

	/**
	 * xss  jackson 配置
	 *
	 * @param properties xss配置
	 * @param xssCleaner 清理器
	 * @return org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
	 * @since 2022/7/5
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer xssJacksonCustomizer(XssProperties properties,
																	  XssCleaner xssCleaner) {
		return builder -> builder.deserializerByType(String.class, new JacksonXssClean(properties, xssCleaner));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = xssProperties.getPathPatterns();
		if (patterns.isEmpty()) {
			patterns.add("/**");
		}
		XssCleanInterceptor interceptor = new XssCleanInterceptor(xssProperties);
		registry.addInterceptor(interceptor)
				.addPathPatterns(patterns)
				.excludePathPatterns(xssProperties.getPathExcludePatterns())
				.order(Ordered.LOWEST_PRECEDENCE);
	}

}