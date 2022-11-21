package cn.com.genhao2.easycode.common.core.spi;

import cn.com.genhao2.easycode.common.core.spi.annotation.SPI;

/**
 * 带spi注解的过滤器
 *
 * @author luyanan
 * @since 2022/11/19
 **/
@SPI(value = "xss")
public interface FilterWithSpi {

	/**
	 * 过滤
	 *
	 * @param name
	 * @return void
	 * @since 2022/11/19
	 */
	void filter(String name);

}
