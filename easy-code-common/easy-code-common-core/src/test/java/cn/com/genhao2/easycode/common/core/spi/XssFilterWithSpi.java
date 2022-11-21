package cn.com.genhao2.easycode.common.core.spi;

/**
 * xss
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public class XssFilterWithSpi implements FilterWithSpi {
	@Override
	public void filter(String name) {
		System.out.println("xss");
	}
}
