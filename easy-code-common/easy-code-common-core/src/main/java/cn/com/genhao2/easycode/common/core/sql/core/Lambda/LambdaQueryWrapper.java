package cn.com.genhao2.easycode.common.core.sql.core.Lambda;

import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;
import cn.com.genhao2.easycode.common.core.sql.core.builder.Query;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Opt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * lambda
 *
 * @author luyanan
 * @since 2022/11/24
 **/
public class LambdaQueryWrapper<T> extends AbstractLambdaWrapper<T, LambdaQueryWrapper<T>>
		implements Query<LambdaQueryWrapper<T>, T, SFunction<T, ?>> {

	protected List<String> selectField = new ArrayList<>();

	public LambdaQueryWrapper() {
//		this((T) null);
	}

	public LambdaQueryWrapper(T entity) {
		super.setEntity(entity);
//		initNeed();
	}

	public LambdaQueryWrapper(Class<T> entityClass) {
		super.setEntityClass(entityClass);
//		initNeed();
	}

	/**
	 * 必要的初始化
	 */
	protected void initNeed() {
		if (null != super.entity) {
			this.entityClass = (Class<T>) this.entity.getClass();
		}


	}

	/**
	 * 子类返回一个自己的新对象
	 */
	@Override
	protected LambdaQueryWrapper<T> instance() {
		return new LambdaQueryWrapper<>();
	}

	/**
	 * 获取sql
	 *
	 * @return Children
	 * @since 2022/11/21
	 */
	@Override
	public String getSql() {

		initNeed();
		if (CollectionUtil.isEmpty(selectField)) {
			selectField.add("*");
		}
		return SqlKeyword.SELECT.getSqlSegment() + " " + selectField.stream().collect(Collectors.joining(",")) + SqlKeyword.FROM.getSqlSegment() + getTableName() +
				Opt.ofBlankAble(tableNameAlias).map(a -> " AS " + tableNameAlias).orElse(" ") + super.getSqlSegment();
	}

	/**
	 * ignore
	 *
	 * @param columns
	 */
	@Override
	public LambdaQueryWrapper<T> select(SFunction<T, ?>... columns) {
		for (SFunction<T, ?> column : columns) {
			String string = columnsToString(column);
			selectField.add(string);
		}
		return typedThis;
	}

	/**
	 * 分页
	 *
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return Children
	 * @since 2022/12/1
	 */
	@Override
	public LambdaQueryWrapper<T> limit(boolean condition, long offset, long limit) {
		return null;
	}
}
