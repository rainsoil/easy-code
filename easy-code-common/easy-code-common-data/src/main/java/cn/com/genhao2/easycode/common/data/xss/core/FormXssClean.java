package cn.com.genhao2.easycode.common.data.xss.core;

import cn.com.genhao2.easycode.common.data.xss.config.XssProperties;
import cn.com.genhao2.easycode.common.data.xss.utils.XssUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

/**
 * @author luyanan
 * @since 2022/7/4
 **/
@ControllerAdvice
@ConditionalOnProperty(
		prefix = XssProperties.PREFIX,
		name = "enabled",
		havingValue = "true",
		matchIfMissing = false
)
@RequiredArgsConstructor
public class FormXssClean {
	private final XssProperties properties;
	private final XssCleaner xssCleaner;

	/**
	 * initBinder
	 *
	 * @param binder 数据绑定
	 * @since 2022/7/5
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 处理前端传来的表单字符串
		binder.registerCustomEditor(String.class, new StringPropertiesEditor(xssCleaner, properties));
	}

	/**
	 * StringPropertiesEditor
	 *
	 * @author luyanan
	 * @since 2022/7/5
	 */
	@Slf4j
	@RequiredArgsConstructor
	public static class StringPropertiesEditor extends PropertyEditorSupport {
		/**
		 * xss清理器
		 *
		 * @since 2022/7/5
		 */

		private final XssCleaner xssCleaner;
		/**
		 * 配置
		 *
		 * @since 2022/7/5
		 */

		private final XssProperties properties;

		@Override
		public String getAsText() {
			Object value = getValue();
			return value != null ? value.toString() : StrUtil.EMPTY;
		}

		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (text == null) {
				setValue(null);
			} else if (XssHolder.isEnabled()) {
				String value = xssCleaner.clean(XssUtil.trim(text, properties.isTrimText()));
				setValue(value);
				log.debug("Request parameter value:{} cleaned up by mica-xss, current value is:{}.", text, value);
			} else {
				setValue(XssUtil.trim(text, properties.isTrimText()));
			}
		}
	}

}