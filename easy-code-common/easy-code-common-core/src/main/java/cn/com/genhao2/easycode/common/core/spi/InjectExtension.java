package cn.com.genhao2.easycode.common.core.spi;

import java.util.Set;

/**
 * 依赖注入
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public interface InjectExtension {


	/**
	 * 扩展类的依赖注入
	 *
	 * @param <T>             扩展类泛型
	 * @param instance        实例
	 * @param extensionLoader ExtensionLoader
	 * @param extensionDir    扩展类路径
	 * @param classLoader     类加载器
	 * @param springInject    是否开启spring 的扩展
	 * @return T  实例对象
	 * @since 2022/11/19
	 */
	<T> T injectExtension(T instance, ExtensionLoader extensionLoader, Set<String> extensionDir, ClassLoader classLoader, boolean springInject);
}
