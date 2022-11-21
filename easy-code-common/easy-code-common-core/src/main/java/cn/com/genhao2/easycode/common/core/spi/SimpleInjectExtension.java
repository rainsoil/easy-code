package cn.com.genhao2.easycode.common.core.spi;

import cn.com.genhao2.easycode.common.core.spi.annotation.Inject;
import cn.com.genhao2.easycode.common.core.spring.SpringContextHolder;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * 简单依赖注入实现
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public class SimpleInjectExtension implements InjectExtension {
	@Override
	public <T> T injectExtension(T instance, ExtensionLoader extensionLoader, Set<String> extensionDir, ClassLoader classLoader, boolean springInject) {
		// 获取实例的所有字段
		for (Field field : ReflectUtil.getFields(instance.getClass())) {
			Object extension = null;
			//只给添加了@Inject 注解的字段赋值
			if (field.isAnnotationPresent(Inject.class)) {
				Inject inject = field.getAnnotation(Inject.class);
				String name = inject.value();
				if (StrUtil.isBlank(name)) {
					name = extensionLoader.getDefaultExtensionName();
				}
				extension = extensionLoader.getExtension(name);
				ReflectUtil.setFieldValue(instance, field, extension);
			} else if (springInject && field.isAnnotationPresent(Autowired.class)) {
				ApplicationContext applicationContext = SpringContextHolder.getApplicationContext();
				// spring注入
				if (applicationContext != null) {
					extension = applicationContext.getBean(field.getType());
				}
				ReflectUtil.setFieldValue(instance, field, extension);
			}
		}
		return instance;
	}
}
