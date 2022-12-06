package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;
import java.util.Map;

/**
 * 修改
 *
 * @author luyanan
 * @since 2022/12/1
 **/
public interface Update<Children, T, R> extends Serializable {

	/**
	 * 插入
	 *
	 * @param entity 实体
	 * @return Children
	 * @since 2022/12/4
	 */
	Children insert(T entity);

	/**
	 * 插入
	 *
	 * @param map
	 * @return Children
	 * @since 2022/12/4
	 */
	Children insert(Map<String, Object> map);



	Children update(T ent);
}
