package cn.com.genhao2.easycode.common.core.sql.core.Lambda;

import cn.com.genhao2.easycode.common.core.sql.core.ColumnCache;
import cn.com.genhao2.easycode.common.core.sql.core.NamingStrategy;
import cn.com.genhao2.easycode.common.core.sql.core.TableInfo;
import cn.com.genhao2.easycode.common.core.sql.core.TableInfoHelper;
import cn.com.genhao2.easycode.common.core.sql.core.builder.AbstractWrapper;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import org.springframework.core.GenericTypeResolver;

import javax.management.ReflectionException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * Lambda 语法使用 Wrapper
 * 统一处理解析 lambda 获取 column
 *
 * @author luyanan
 * @since 2022/11/24
 **/
public abstract class AbstractLambdaWrapper<T, Children extends AbstractLambdaWrapper<T, Children>>
		extends AbstractWrapper<T, SFunction<T, ?>, Children> {
	/**
	 * 实体类型(主要用于确定泛型以及取TableInfo缓存)
	 */
	protected Class<T> entityClass;
	private Map<String, ColumnCache> columnMap = null;
	private boolean initColumnMap = false;

	protected NamingStrategy namingStrategy = NamingStrategy.UNDERLINE_TO_CAMEL;
	/**
	 * 数据库表映射实体类
	 */
	protected T entity;

	public T getEntity() {
		return entity;
	}

	public Children setEntity(T entity) {
		this.entity = entity;
		return typedThis;
	}

	public Children setEntityClass(Class<T> entityClass) {
		if (entityClass != null) {
			this.entityClass = entityClass;
		}
		return typedThis;
	}


	@SafeVarargs
	protected final String columnsToString(SFunction<T, ?>... columns) {
		return columnsToString(true, columns);
	}

	@SafeVarargs
	protected final String columnsToString(boolean onlyColumn, SFunction<T, ?>... columns) {
		return columnsToString(onlyColumn, Arrays.asList(columns));
	}

	protected final String columnsToString(boolean onlyColumn, List<SFunction<T, ?>> columns) {
		return columns.stream().map(i -> columnToString(i, onlyColumn)).collect(joining(","));
	}

	@Override
	public String getTableName() {
		if (StrUtil.isBlank(this.tableName)) {
			TableInfo tableInfo = TableInfoHelper.getTableInfo(this.getEntityClass(), namingStrategy);
			this.tableName = tableInfo.getTableName();
		}
		return this.tableName;

	}

	@Override
	protected String columnToString(SFunction<T, ?> column) {
		return columnToString(column, true);
	}

	protected String columnToString(SFunction<T, ?> column, boolean onlyColumn) {
		ColumnCache cache = getColumnCache(column);
		return onlyColumn ? cache.getColumnSelect() : cache.getColumn();
	}


	private ColumnCache getColumnCache(String fieldName, Class<?> lambdaClass) {
		ColumnCache columnCache = columnMap.get(fieldName);
		Assert.notNull(columnCache, "can not find lambda cache for this property [%s] of entity [%s]",
				fieldName, lambdaClass.getName());
		return columnCache;
	}

	public static String methodToProperty(String name) {
		if (name.startsWith("is")) {
			name = name.substring(2);
		} else if (name.startsWith("get") || name.startsWith("set")) {
			name = name.substring(3);
		} else {
			throw new IllegalArgumentException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
		}

		if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
			name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
		}

		return name;
	}

	/**
	 * 获取 SerializedLambda 对应的列信息，从 lambda 表达式中推测实体类
	 * <p>
	 * 如果获取不到列信息，那么本次条件组装将会失败
	 *
	 * @return 列
	 */
	protected ColumnCache getColumnCache(SFunction<T, ?> column) {
		LambdaMeta meta = LambdaUtils.extract(column);
		String fieldName = methodToProperty(meta.getImplMethodName());
		Class<?> instantiatedClass = meta.getInstantiatedClass();
		tryInitCache(instantiatedClass);
		return getColumnCache(fieldName, instantiatedClass);
	}

	private void tryInitCache(Class<?> lambdaClass) {
		if (!initColumnMap) {
			final Class<T> entityClass = getEntityClass();
			if (entityClass != null) {
				lambdaClass = entityClass;
			}
			columnMap = LambdaUtils.getColumnMap(lambdaClass, namingStrategy);
			Assert.notNull(columnMap, "can not find lambda cache for this entity [%s]", lambdaClass.getName());
			initColumnMap = true;
		}
	}

	public Class<T> getEntityClass() {
		if (entityClass == null && entity != null) {
			entityClass = (Class<T>) entity.getClass();
		}
		return entityClass;
	}
}