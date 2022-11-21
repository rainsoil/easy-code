package cn.com.genhao2.easycode.common.core.cache.caffeine;

import cn.com.genhao2.easycode.common.core.cache.condition.CacheCondition;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 缓存自动配置类
 *
 * @author luyanan
 * @since 2022/11/18
 **/
@Slf4j
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnClass({Caffeine.class, CaffeineCacheManager.class})
@AutoConfigureBefore(CacheAutoConfiguration.class)
@Conditional(CacheCondition.class)
public class CaffeineAutoCacheConfiguration {
	/**
	 * 创建缓存管理器
	 *
	 * @param cacheProperties cacheProperties
	 * @param caffeine        caffeine
	 * @param caffeineSpec    caffeineSpec
	 * @param cacheLoader     cacheLoader
	 * @return cn.com.genhao2.easycode.common.core.cache.caffeine.CaffeineAutoCacheManager
	 * @since 2022/11/19
	 */
	private static CaffeineAutoCacheManager createCacheManager(CacheProperties cacheProperties,
															   ObjectProvider<Caffeine<Object, Object>> caffeine,
															   ObjectProvider<CaffeineSpec> caffeineSpec,
															   ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
		CaffeineAutoCacheManager cacheManager = new CaffeineAutoCacheManager();
		setCacheBuilder(cacheProperties, caffeineSpec.getIfAvailable(), caffeine.getIfAvailable(), cacheManager);
		cacheLoader.ifAvailable(cacheManager::setCacheLoader);
		return cacheManager;
	}

	/**
	 * 设置构建器
	 *
	 * @param cacheProperties cacheProperties
	 * @param caffeineSpec    caffeineSpec
	 * @param caffeine        caffeine
	 * @param cacheManager    cacheManager
	 * @since 2022/11/19
	 */
	private static void setCacheBuilder(CacheProperties cacheProperties,
										@Nullable CaffeineSpec caffeineSpec,
										@Nullable Caffeine<Object, Object> caffeine,
										CaffeineCacheManager cacheManager) {
		String specification = cacheProperties.getCaffeine().getSpec();
		if (StringUtils.hasText(specification)) {
			cacheManager.setCacheSpecification(specification);
		} else if (caffeineSpec != null) {
			cacheManager.setCaffeineSpec(caffeineSpec);
		} else if (caffeine != null) {
			cacheManager.setCaffeine(caffeine);
		}
	}

	/**
	 * cacheManagerCustomizers
	 *
	 * @param customizers customizers
	 * @return org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers
	 * @since 2022/11/19
	 */
	@Bean
	@ConditionalOnMissingBean
	public CacheManagerCustomizers cacheManagerCustomizers(ObjectProvider<CacheManagerCustomizer<?>> customizers) {
		return new CacheManagerCustomizers(customizers.orderedStream().collect(Collectors.toList()));
	}

	/**
	 * cacheManager
	 *
	 * @param cacheProperties 配置
	 * @param customizers     自定义
	 * @param caffeine        caffeine
	 * @param caffeineSpec    caffeineSpec
	 * @param cacheLoader     cacheLoader
	 * @return org.springframework.cache.CacheManager
	 * @since 2022/11/19
	 */
	@Bean("cacheResolver")
	public CacheManager cacheManager(CacheProperties cacheProperties,
									 CacheManagerCustomizers customizers,
									 ObjectProvider<Caffeine<Object, Object>> caffeine,
									 ObjectProvider<CaffeineSpec> caffeineSpec,
									 ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
		CaffeineAutoCacheManager cacheManager = createCacheManager(cacheProperties, caffeine, caffeineSpec, cacheLoader);
		List<String> cacheNames = cacheProperties.getCacheNames();
		if (!CollectionUtils.isEmpty(cacheNames)) {
			cacheManager.setCacheNames(cacheNames);
		}
		log.debug("启动{}配置", CaffeineAutoCacheConfiguration.class.getName());
		return customizers.customize(cacheManager);
	}


}
