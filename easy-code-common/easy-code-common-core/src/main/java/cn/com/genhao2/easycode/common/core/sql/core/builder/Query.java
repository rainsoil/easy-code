package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public interface Query<Children, T, R> extends Serializable {


	/**
	 * ignore
	 */
	@SuppressWarnings("unchecked")
	Children select(R... columns);


}
