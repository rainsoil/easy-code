package cn.com.genhao2.easycode.common.core.sql.core;

import cn.com.genhao2.easycode.common.core.sql.core.annotation.TableField;
import cn.com.genhao2.easycode.common.core.sql.core.annotation.TableId;
import cn.com.genhao2.easycode.common.core.sql.core.annotation.TableName;
import cn.com.genhao2.easycode.common.core.sql.core.enums.IdType;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Opt;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 实体类反射表辅助类
 *
 * @author luyanan
 * @since 2022/11/26
 **/
@UtilityClass
public class TableInfoHelper {


	/**
	 * 获取表的信息
	 *
	 * @param clazz          实体类class
	 * @param namingStrategy 命名策略
	 * @return cn.com.genhao2.easycode.common.core.sql.core.TableInfo
	 * @since 2022/11/28
	 */
	public TableInfo getTableInfo(Class<?> clazz, NamingStrategy namingStrategy) {

		String tableName = null;
		TableName tableAnnotation = clazz.getAnnotation(TableName.class);
		if (null != tableAnnotation && StrUtil.isNotBlank(tableAnnotation.value())) {
			tableName = tableAnnotation.value();
		} else {
			tableName = namingStrategy.camelToUnderline(clazz.getSimpleName());
		}


		// 获取字段
		Field[] fields = ReflectUtil.getFields(clazz);
		IdType idType = Arrays.stream(fields).filter(a -> a.isAnnotationPresent(TableId.class)).map(a -> a.getAnnotation(TableId.class).type()).findFirst().orElse(null);
		List<TableFieldInfo> fieldInfoList = Arrays.stream(fields).map(a -> {
			TableFieldInfo tableFieldInfo = new TableFieldInfo();
			tableFieldInfo.setJavaType(a.getType());
			tableFieldInfo.setJavaColumn(a.getName());
			String jdbcName = namingStrategy.camelToUnderline(a.getName());
			TableField tableField = a.getAnnotation(TableField.class);
			if (null != tableField) {
				if (!tableField.exist()) {
					jdbcName = null;
				}
				if (StrUtil.isNotBlank(tableField.value())) {
					jdbcName = tableField.value();
				}
			}

			if (a.isAnnotationPresent(TableId.class)) {
				TableId tableId = a.getAnnotation(TableId.class);
				if (StrUtil.isNotBlank(tableId.value())) {
					jdbcName = tableId.value();
				}
				tableFieldInfo.setPrimaryKey(true);
			}

			if (StrUtil.isBlank(jdbcName)) {
				return null;
			}
			tableFieldInfo.setJdbcColumn(jdbcName);
			return tableFieldInfo;
		}).filter(a -> null != a).collect(Collectors.toList());
		TableFieldInfo idField
				= fieldInfoList.stream().filter(a -> a.isPrimaryKey()).findFirst().orElse(null);
		return new TableInfo()
				.setEntityType(clazz)
				.setTableName(tableName)
				.setIdType(Opt.ofNullable(idType).filter(type -> !type.equals(IdType.NONE)).orElse(IdType.AUTO))
				.setTableFieldInfos(fieldInfoList)
				.setKeyType(Opt.ofNullable(idField).map(id -> id.getJavaType()).orElse(null))
				.setKeyJavaColumn(Opt.ofNullable(idField).map(id -> id.getJavaColumn()).orElse(null))
				.setKeyJdbcColumn(Opt.ofNullable(idField).map(id -> id.getJdbcColumn()).orElse(null));
	}


}
