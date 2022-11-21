package cn.com.genhao2.easycode.common.core.cache;


import cn.com.genhao2.easycode.common.core.cache.caffeine.CaffeineAutoCacheConfiguration;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@EnableCaching
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CaffeineAutoCacheConfigurationTest.class)
@AutoConfigureCache(cacheProvider = CacheType.CAFFEINE)
@Import(CaffeineAutoCacheConfiguration.class)
public class CaffeineAutoCacheConfigurationTest {

	@Autowired
	private CacheManager cacheManager;


	@Test
	public void caffeineTest() {
		log.info("开启caffeine缓存的测试");
		Class<? extends CacheManager> aClass = cacheManager.getClass();
		log.info("当前使用的缓存为:{}", aClass.getName());
		Cache cache = cacheManager.getCache("test_cache#1m");
		String key = "caffeine_key";
		String value = "1";
		cache.put(key, value);
		Assert.isTrue(cache.get(key, String.class).equals(value));
		try {
			Thread.sleep(1 * 61 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.isTrue(StrUtil.isBlank(cache.get(key, String.class)));
	}
}