package cn.com.genhao2.easycode.common.core.sql.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 字段缓存
 *
 * @author luyanan
 * @since 2022/11/24
 **/
@Data
public class ColumnCache implements Serializable {

	private static final long serialVersionUID = -4586291538088403456L;

	/**
	 * 使用 column
	 */
	private String column;
	/**
	 * 查询 column
	 */
	private String columnSelect;


	public ColumnCache(String column, String columnSelect) {
		this.column = column;
		this.columnSelect = columnSelect;
	}
}
