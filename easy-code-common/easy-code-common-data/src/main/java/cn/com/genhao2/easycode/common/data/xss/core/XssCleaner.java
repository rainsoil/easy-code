package cn.com.genhao2.easycode.common.data.xss.core;

/**
 * xss清理器
 *
 * @author luyanan
 * @since 2022/7/4
 **/
public interface XssCleaner {

	/**
	 * 清理 html
	 *
	 * @param html html
	 * @return 清理后的数据
	 */
	String clean(String html);

}
