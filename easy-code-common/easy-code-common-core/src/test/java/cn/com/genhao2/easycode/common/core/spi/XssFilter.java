package cn.com.genhao2.easycode.common.core.spi;

/**
 * xss
 *
 * @author luyanan
 * @since 2022/11/19
 **/
public class XssFilter implements Filter {
	@Override
	public void filter(String name) {
		System.out.println("xss");
	}
}
