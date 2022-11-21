package cn.com.genhao2.easycode.common.core.cache.utils;

import cn.com.genhao2.easycode.common.core.cache.CacheConstants;
import lombok.experimental.UtilityClass;
import org.springframework.boot.convert.DurationStyle;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 缓存名称工具类
 *
 * @author luyanan
 * @since 2022/11/15
 **/
@UtilityClass
public class CacheNameUtils {


	/**
	 * 解析缓存名称
	 *
	 * @param cacheName 缓存名称
	 * @return cn.com.genhao2.easycode.common.core.cache.utils.CacheNameUtils.CacheNameVo
	 * @since 2022/11/15
	 */
	public Duration parserCacheName(String cacheName) {

		if (!cacheName.contains(CacheConstants.SEPARATOR)) {

			return null;
		}
		String[] split = cacheName.split(CacheConstants.SEPARATOR);
		if (split.length < 2) {
			return null;
		}
		Duration duration = DurationStyle.detectAndParse(split[1], ChronoUnit.SECONDS);
		return duration;
	}


}
