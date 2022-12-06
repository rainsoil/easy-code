package cn.com.genhao2.easycode.common.core.sql.core.Lambda;

import cn.com.genhao2.easycode.common.core.sql.core.*;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Locale.ENGLISH;

/**
 * Lambda 解析工具类
 *
 * @author luyanan
 * @since 2022/11/24
 **/
public class LambdaUtils {
	/**
	 * 字段映射
	 */
	private static final Map<String, Map<String, ColumnCache>> COLUMN_CACHE_MAP = new ConcurrentHashMap<>();

	/**
	 * 该缓存可能会在任意不定的时间被清除
	 *
	 * @param func 需要解析的 lambda 对象
	 * @param <T>  类型，被调用的 Function 对象的目标类型
	 * @return 返回解析后的结果
	 */
	@SneakyThrows
	public static <T> LambdaMeta extract(SFunction<T, ?> func) {
		// 1. IDEA 调试模式下 lambda 表达式是一个代理
		if (func instanceof Proxy) {
			InvocationHandler handler = Proxy.getInvocationHandler(func);
			ReflectUtil.setAccessible(handler.getClass().getDeclaredField("val$target")).get(handler);
			return new IdeaProxyLambdaMeta((Proxy) func);
		}
		// 2. 反射读取
		try {
			Method method = func.getClass().getDeclaredMethod("writeReplace");
			return new ReflectLambdaMeta((SerializedLambda) ReflectUtil.setAccessible(method).invoke(func));
		} catch (Throwable e) {
			// 3. 反射失败使用序列化的方式读取
			return new ShadowLambdaMeta(SerializedLambda.extract(func));
		}
	}

	/**
	 * 格式化 key 将传入的 key 变更为大写格式
	 *
	 * <pre>
	 *     Assert.assertEquals("USERID", formatKey("userId"))
	 * </pre>
	 *
	 * @param key key
	 * @return 大写的 key
	 */
	public static String formatKey(String key) {
		return key.toUpperCase(ENGLISH);
	}


	public static Map<String, ColumnCache> getColumnMap(Class<?> lambdaClass, NamingStrategy namingStrategy) {
		Map<String, ColumnCache> columnCacheMap = COLUMN_CACHE_MAP.get(lambdaClass.getName());

		if (null != columnCacheMap) {
			return columnCacheMap;
		}
		// 初始化
		TableInfo info = TableInfoHelper.getTableInfo(lambdaClass, namingStrategy);
		if (null != info) {

			return createColumnCacheMap(info);
		}
		return null;

	}

	/**
	 * 缓存实体字段 MAP 信息
	 *
	 * @param tableInfo 表信息
	 * @return java.util.Map<java.lang.String, cn.com.genhao2.easycode.common.core.sql.core.ColumnCache> 缓存map
	 * @since 2022/11/29
	 */
	protected static Map<String, ColumnCache> createColumnCacheMap(TableInfo tableInfo) {
		Map<String, ColumnCache> map = new HashMap<>();
		for (TableFieldInfo tableFieldInfo : tableInfo.getTableFieldInfos()) {
			map.put(tableFieldInfo.getJavaColumn(), new ColumnCache(tableFieldInfo.getJavaColumn(), tableFieldInfo.getJdbcColumn()));
		}
		return map;
	}
}
