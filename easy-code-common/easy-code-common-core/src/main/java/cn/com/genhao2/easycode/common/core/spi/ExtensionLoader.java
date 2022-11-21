package cn.com.genhao2.easycode.common.core.spi;

import cn.com.genhao2.easycode.common.core.spi.annotation.SPI;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * SPI扩展机制
 *
 * @param <T> 扩展类
 * @author luyanan
 * @since 2022/11/19
 **/
@Slf4j
public class ExtensionLoader<T> {


	/**
	 * 接口类与实例类的 缓存
	 *
	 * @since 2022/11/19
	 */

	private static final ConcurrentHashMap<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<>();
	/**
	 * SPI value 拆分正则
	 *
	 * @since 2022/11/19
	 */

	private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");
	/**
	 * 扩展点父路径
	 *
	 * @since 2022/11/19
	 */

	private final String parentExtensionDir = "META-INF/";
	/**
	 * 保存扫描出来的配置文件信息
	 *
	 * @since 2022/11/19
	 */
	private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();
	/**
	 * 扩展名和扩展对象的实例缓存
	 *
	 * @since 2022/11/19
	 */

	private final ConcurrentHashMap<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<>();
	/**
	 * 扩展类与扩展名的缓存
	 *
	 * @since 2022/11/19
	 */

	private final ConcurrentHashMap<Class<?>, String> cachedNames = new ConcurrentHashMap<>();
	/**
	 * 扩展点接口
	 *
	 * @since 2022/11/19
	 */

	private Class<T> type;
	/**
	 * 依赖注入
	 *
	 * @since 2022/11/19
	 */

	private InjectExtension injectExtension;
	/**
	 * 扩展点路径
	 *
	 * @since 2022/11/19
	 */

	private Set<String> extensionDir = new HashSet<>();
	/**
	 * 默认的扩展名(SPI注解的value值)
	 *
	 * @since 2022/11/19
	 */

	private String cachedDefaultName;


	/**
	 * 类加载器
	 *
	 * @since 2022/11/19
	 */

	private ClassLoader classLoader;

	/**
	 * 是否开启spring的依赖注入
	 *
	 * @since 2022/11/19
	 */

	private boolean springInject = false;

	/**
	 * 是否初始化
	 *
	 * @since 2022/11/19
	 */

	private boolean init = false;


	public ExtensionLoader(Class<T> type, InjectExtension injectExtension, Set<String> extensionDir, ClassLoader classLoader) {
		this.type = type;
		this.classLoader = classLoader;
		this.injectExtension = injectExtension;
		for (String s : extensionDir) {
			this.extensionDir.add((this.parentExtensionDir + s).replaceAll("/+", "/"));
		}
	}


	public ExtensionLoader(Class<T> type) {
		this.type = type;
	}

	/**
	 * 获取扩展类
	 *
	 * @param type 扩展类接口
	 * @param <T>  扩展类泛型
	 * @return cn.com.genhao2.easycode.common.core.spi.ExtensionLoader<T>
	 * @since 2022/11/19
	 */
	public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
		return new ExtensionLoader<T>(type);
	}

	/**
	 * 设置依赖注入的实现类
	 *
	 * @param injectExtension 依赖注入实现类
	 * @return cn.com.genhao2.easycode.common.core.spi.ExtensionLoader<T>
	 * @since 2022/11/19
	 */
	public ExtensionLoader<T> injectExtension(InjectExtension injectExtension) {
		Assert.isNull(injectExtension);
		this.injectExtension = injectExtension;
		return this;
	}

	/**
	 * 是否开启spring的依赖注入
	 *
	 * @param enable 是否开启
	 * @return cn.com.genhao2.easycode.common.core.spi.ExtensionLoader<T>
	 * @since 2022/11/19
	 */
	public ExtensionLoader<T> enableSpringInject(boolean enable) {
		this.springInject = enable;
		return this;
	}

	/**
	 * 设置扩展文件在的文件夹
	 *
	 * @param extensionDir 文件夹
	 * @return cn.com.genhao2.easycode.common.core.spi.ExtensionLoader<T>
	 * @since 2022/11/19
	 */
	public ExtensionLoader<T> extensionDir(Set<String> extensionDir) {

		this.extensionDir.addAll(extensionDir);
		return this;
	}

	/**
	 * 根据名称获取配置的扩展点
	 *
	 * @param name 扩展点名称
	 * @return T 扩展点
	 * @since 2022/11/19
	 */
	public T getExtension(String name) {

		if (StrUtil.isEmpty(name)) {
			throw new IllegalArgumentException("name == null");
		}

		// 初始化
		init();

		Holder<Object> holder = getOrCreateHolder(name);
		Object instance = holder.get();
		if (null == instance) {
			synchronized (holder) {
				instance = holder.get();
				if (null == instance) {
					// 根据名称创建
					instance = createExtension(name);
					// 再保存起来
					holder.set(instance);
				}
			}
		}
		return (T) instance;
	}


	/**
	 * 获取默认的扩展点
	 *
	 * @return T 扩展点对象
	 * @since 2022/11/19
	 */
	public T getDefaultExtension() {

		init();
		if (StrUtil.isBlank(cachedDefaultName)) {
			return null;
		}
		return getExtension(cachedDefaultName);
	}

	/**
	 * 默认的名称
	 *
	 * @return java.lang.String
	 * @since 2022/11/19
	 */
	public String getDefaultExtensionName() {
		return this.cachedDefaultName;
	}


	/**
	 * 判断名称为@name的扩展点是否存在
	 *
	 * @param name 扩展点名称
	 * @return boolean 是否存在
	 * @since 2022/11/20
	 */
	public boolean hasExtension(String name) {
		if (StrUtil.isBlank(name)) {
			throw new NullPointerException("name ==null");
		}
		init();
		Class<?> clazz = this.getExtensionClasses().get(name);
		return clazz != null;
	}


	/**
	 * 获取所有的扩展点名称
	 *
	 * @return java.util.List<java.lang.String>
	 * @since 2022/11/20
	 */
	public List<String> getExtensionNames() {

		init();
		return cachedClasses.get().keySet().stream().collect(Collectors.toList());
	}

	/************************private methid*********************/

	/**
	 * 根据扩展名创建实例
	 *
	 * @param name 扩展类接口名称
	 * @return T 实例对象
	 * @since 2022/11/19
	 */
	private T createExtension(String name) {

		Class<?> clazz = getExtensionClasses().get(name);
		if (null == clazz) {
			throw new NullPointerException("could not find type extension ");
		}

		T instance = null;
		try {
			instance = (T) EXTENSION_INSTANCES.get(clazz);

			if (instance == null) {
				EXTENSION_INSTANCES.putIfAbsent(clazz, clazz.newInstance());
				instance = (T) EXTENSION_INSTANCES.get(clazz);
			}
			injectExtension(instance);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;

	}

	/**
	 * 依赖注入
	 *
	 * @param instance 实例对象
	 * @return T
	 * @since 2022/11/19
	 */
	private T injectExtension(T instance) {
		return injectExtension.injectExtension(instance, this, extensionDir, this.classLoader, springInject);
	}

	/**
	 * 创建或者获取Holder
	 *
	 * @param name 名称
	 * @return cn.com.genhao2.easycode.common.core.spi.Holder<java.lang.Object>
	 * @since 2022/11/19
	 */
	private Holder<Object> getOrCreateHolder(String name) {
		Holder<Object> holder = cachedInstances.get(name);
		if (holder == null) {
			cachedInstances.putIfAbsent(name, new Holder<>());
			holder = cachedInstances.get(name);
		}
		return holder;
	}

	/**
	 * 初始化
	 *
	 * @since 2022/11/19
	 */
	private void init() {

		if (init) {
			return;
		}
		if (null == this.injectExtension) {
			this.injectExtension = new SimpleInjectExtension();
		}

		if (null == type) {
			throw new IllegalArgumentException("Extension type == null");
		}
		// type 必须为接口
		if (!type.isInterface()) {
			throw new IllegalArgumentException("Extension type (" + type + ")is not a  interface");
		}

		for (String s : extensionDir) {
			this.extensionDir.add((this.parentExtensionDir + s).replaceAll("/+", "/"));
		}
		this.extensionDir.add(this.parentExtensionDir);
		// 扫描扩展类
		getExtensionClasses();
	}


	/**
	 * 扫描指定目录下的配置信息，然后保存到一个hashmap中
	 *
	 * @return java.util.Map<java.lang.String, java.lang.Class < ?>>
	 * @since 2022/11/19
	 */
	private Map<String, Class<?>> getExtensionClasses() {

		Map<String, Class<?>> classes = cachedClasses.get();
		if (null == classes) {
			synchronized (cachedClasses) {
				//加载配置文件
				classes = loadExtensionClasses();
				cachedClasses.set(classes);
			}
		}
		return classes;
	}

	/**
	 * 加载配置文件
	 *
	 * @return java.util.Map<java.lang.String, java.lang.Class < ?>>
	 * @since 2022/11/19
	 */
	private Map<String, Class<?>> loadExtensionClasses() {

		// 缓存默认的扩展点名称
		cacheDefaultExtensionName();

		Map<String, Class<?>> extensionClasses = new HashMap<>(8);
		for (String dir : this.extensionDir) {
			loadDirectory(extensionClasses, dir);
		}
		return extensionClasses;
	}


	/**
	 * 加载配置文件
	 *
	 * @param extensionClasses 扩展类
	 * @param dir              目录
	 * @since 2022/11/19
	 */
	private void loadDirectory(Map<String, Class<?>> extensionClasses, String dir) {
		String fileName = dir + type.getName();

		Enumeration<URL> urls;
		ClassLoader classLoader = findClassLoader();
		try {
			if (null != classLoader) {
				urls = classLoader.getResources(fileName);
			} else {
				urls = ClassLoader.getSystemResources(fileName);
			}

			if (urls != null) {
				while (urls.hasMoreElements()) {
					URL url = urls.nextElement();
					loadResource(extensionClasses, classLoader, url, type);
				}
			}
		} catch (IOException e) {
			log.error("Exception occurred when loading extension class (interface: " +
					type + ", description file: " + fileName + ").", e);
		}
	}

	/**
	 * 扩展类加载
	 *
	 * @param extensionClasses 扩展类存储map
	 * @param classLoader      类加载器
	 * @param url              地址路径
	 * @param type             扩展类接口
	 * @since 2022/11/19
	 */
	private void loadResource(Map<String, Class<?>> extensionClasses, ClassLoader classLoader, URL url, Class<T> type) {
		try {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {

				String line;

				while ((line = reader.readLine()) != null) {


					int ci = line.indexOf("#");
					if (ci >= 0) {
						line = line.substring(0, ci);
					}
					line = line.trim();
					if (line.length() > 0) {

						String name = null;
						int i = line.indexOf("=");
						if (i > 0) {
							name = line.substring(0, i).trim();
							line = line.substring(i + 1).trim();
						}
						if (line.length() > 0) {
							loadClass(extensionClasses, Class.forName(line, true, classLoader), name, type);
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 加载扩展类
	 *
	 * @param extensionClasses 扩展类存储
	 * @param clazz            类
	 * @param name             扩展名称
	 * @param type             扩展接口
	 * @since 2022/11/19
	 */
	private void loadClass(Map<String, Class<?>> extensionClasses, Class<?> clazz, String name, Class<T> type) {
		if (!type.isAssignableFrom(clazz)) {
			throw new IllegalStateException("Error occurred when loading extension class (interface: " +
					type + ", class line: " + clazz.getName() + "), class "
					+ clazz.getName() + " is not subtype of interface.");
		}
		String[] names = NAME_SEPARATOR.split(name);
		if (!ArrayUtil.isEmpty(names)) {
			for (String s : names) {
				cacheNames(clazz, s);
				saveInExtensionClass(extensionClasses, clazz, s, type);
			}
		}
	}

	/**
	 * 将扩展类信息保存到extensionClasses 中
	 *
	 * @param extensionClasses extensionClasses
	 * @param clazz            扩展类
	 * @param name             扩展名称
	 * @param type             扩展类接口
	 * @since 2022/11/19
	 */
	private void saveInExtensionClass(Map<String, Class<?>> extensionClasses, Class<?> clazz, String name, Class<?> type) {

		Class<?> c = extensionClasses.get(name);
		if (c == null) {
			extensionClasses.put(name, clazz);
		} else if (c != clazz) {
			throw new IllegalStateException("Duplicate extension " + type.getName() + " name " + name + " on " + c.getName() + " and " + clazz.getName());

		}
	}

	/**
	 * 扩展类与扩展名的缓存
	 *
	 * @param clazz 扩展类
	 * @param name  扩展名
	 * @since 2022/11/19
	 */
	private void cacheNames(Class<?> clazz, String name) {
		if (!cachedNames.contains(clazz)) {
			cachedNames.put(clazz, name);
		}
	}

	/**
	 * 类加载器
	 *
	 * @return java.lang.ClassLoader
	 * @since 2022/11/19
	 */
	private ClassLoader findClassLoader() {
		return this.classLoader != null ? this.classLoader : ExtensionLoader.class.getClassLoader();
	}

	/**
	 * 缓存默认的扩展点
	 *
	 * @since 2022/11/19
	 */
	private void cacheDefaultExtensionName() {

		// 只有包含SPI注解的 才有默认的扩展点,否则没有
		if (!type.isAnnotationPresent(SPI.class)) {
			return;
		}
		SPI spi = type.getAnnotation(SPI.class);
		if (null == spi) {
			return;
		}
		String value = spi.value();
		if ((value = value.trim()).length() > 0) {
			String[] names = NAME_SEPARATOR.split(value);

			if (names.length > 1) {
				throw new IllegalStateException("More than 1 default extension name on extension " + type.getName()
						+ ": " + Arrays.toString(names));
			}
			if (names.length == 1) {
				cachedDefaultName = names[0];
			}
		}

	}


}




