package cn.com.genhao2.easycode.common.core.spi;

/**
 * 过滤器
 *
 * @author luyanan
 * @since 2022/11/19
 **/

public interface Filter {


	/**
	 * 过滤
	 *
	 * @param name
	 * @return void
	 * @since 2022/11/19
	 */
	void filter(String name);
}
