package cn.com.genhao2.easycode.common.data.xss.config;

import cn.com.genhao2.easycode.common.core.constants.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * xss配置文件
 *
 * @author luyanan
 * @since 2022/7/4
 **/
@Data
@ConfigurationProperties(prefix = XssProperties.PREFIX)
public class XssProperties {


	public static final String PREFIX = Constants.PROPERTIES_PREFIX + "xss";


	/**
	 * 开启xss
	 */
	private boolean enabled = true;
	/**
	 * 全局：对文件进行首尾 trim
	 */
	private boolean trimText = true;
	/**
	 * 模式：clear 清理（默认），escape 转义
	 */
	private Mode mode = Mode.clear;
	/**
	 * [clear 专用] prettyPrint，默认关闭： 保留换行
	 */
	private boolean prettyPrint = false;
	/**
	 * [clear 专用] 使用转义，默认关闭
	 */
	private boolean enableEscape = false;
	/**
	 * 拦截的路由，默认为空
	 */
	private List<String> pathPatterns = new ArrayList<>();
	/**
	 * 放行的路由，默认为空
	 */
	private List<String> pathExcludePatterns = new ArrayList<>();

	public enum Mode {
		/**
		 * 清理
		 */
		clear,
		/**
		 * 转义
		 */
		escape;
	}

}
