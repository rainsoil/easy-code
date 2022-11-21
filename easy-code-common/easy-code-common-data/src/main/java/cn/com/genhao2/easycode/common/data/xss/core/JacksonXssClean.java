package cn.com.genhao2.easycode.common.data.xss.core;

import cn.com.genhao2.easycode.common.data.xss.config.XssProperties;
import cn.com.genhao2.easycode.common.data.xss.utils.XssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * jackson xss 处理
 *
 * @author luyanan
 * @since 2022/7/4
 **/
@Slf4j
@RequiredArgsConstructor
public class JacksonXssClean extends XssCleanDeserializerBase {
	private final XssProperties properties;
	private final XssCleaner xssCleaner;

	@Override
	public String clean(String text) throws IOException {
		if (XssHolder.isEnabled()) {
			String value = xssCleaner.clean(XssUtil.trim(text, properties.isTrimText()));
			log.debug("Json property value:{} cleaned up by mica-xss, current value is:{}.", text, value);
			return value;
		} else {
			return XssUtil.trim(text, properties.isTrimText());
		}
	}

}