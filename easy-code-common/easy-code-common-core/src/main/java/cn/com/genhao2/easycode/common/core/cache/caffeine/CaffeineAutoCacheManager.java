package cn.com.genhao2.easycode.common.core.cache.caffeine;

import cn.com.genhao2.easycode.common.core.cache.utils.CacheNameUtils;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.CaffeineSpec;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Objects;

/**
 * caffeine 缓存自动配置超时时间
 *
 * @author luyanan
 * @since 2022/11/15
 **/
public class CaffeineAutoCacheManager extends CaffeineCacheManager {

	private static final Field CACHE_LOADER_FIELD;

	static {
		CACHE_LOADER_FIELD = Objects.requireNonNull(ReflectionUtils.findField(CaffeineCacheManager.class, "cacheLoader"));
		CACHE_LOADER_FIELD.setAccessible(true);
	}

	@Nullable
	private CaffeineSpec caffeineSpec = null;

	public CaffeineAutoCacheManager() {
		super();
	}

	public CaffeineAutoCacheManager(String... cacheNames) {
		super(cacheNames);
	}

	@Nullable
	@SuppressWarnings("unchecked")
	protected CacheLoader<Object, Object> getCacheLoader() {
		return (CacheLoader<Object, Object>) ReflectionUtils.getField(CACHE_LOADER_FIELD, this);
	}

	@Override
	public void setCaffeine(Caffeine<Object, Object> caffeine) {
		throw new IllegalArgumentException("mica-caffeine not support customization Caffeine bean，you can customize CaffeineSpec bean.");
	}

	@Override
	public void setCaffeineSpec(CaffeineSpec caffeineSpec) {
		super.setCaffeineSpec(caffeineSpec);
		this.caffeineSpec = caffeineSpec;
	}

	@Override
	public void setCacheSpecification(String cacheSpecification) {
		super.setCacheSpecification(cacheSpecification);
		this.caffeineSpec = CaffeineSpec.parse(cacheSpecification);
	}

	/**
	 * Build a common Caffeine Cache instance for the specified cache name,
	 * using the common Caffeine configuration specified on this cache manager.
	 *
	 * @param name the name of the cache
	 * @return the native Caffeine Cache instance
	 * @see #createCaffeineCache
	 */
	@Override
	protected com.github.benmanes.caffeine.cache.Cache<Object, Object> createNativeCaffeineCache(String name) {
		Duration duration = CacheNameUtils.parserCacheName(name);
		if (null == duration) {
			return super.createNativeCaffeineCache(name);
		}
		// 转换时间，支持时间单位例如：300ms，第二个参数是默认单位
		Caffeine<Object, Object> cacheBuilder;
		if (this.caffeineSpec != null) {
			cacheBuilder = Caffeine.from(caffeineSpec);
		} else {
			cacheBuilder = Caffeine.newBuilder();
		}
		CacheLoader<Object, Object> cacheLoader = getCacheLoader();
		if (cacheLoader == null) {
			return cacheBuilder.expireAfterAccess(duration).build();
		} else {
			return cacheBuilder.expireAfterAccess(duration).build(cacheLoader);
		}
	}


}
