package cn.com.genhao2.easycode.common.core.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页响应
 *
 * @param <T> 泛型
 * @author luyanan
 * @since 2022/9/17
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageInfo<T> {

	/**
	 * 当前页数
	 *
	 * @since 2021/12/1
	 */

	private long current;


	/**
	 * 每页条数
	 *
	 * @since 2021/12/1
	 */

	private long size;


	/**
	 * 返回内容
	 *
	 * @since 2021/12/1
	 */

	private List<T> records;


	/**
	 * 统计
	 *
	 * @since 2021/12/1
	 */

	private long total;

	/**
	 * 页数
	 *
	 * @since 2021/12/1
	 */

	private long pages;
}
