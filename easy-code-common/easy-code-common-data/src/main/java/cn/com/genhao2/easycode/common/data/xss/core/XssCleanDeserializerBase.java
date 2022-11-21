package cn.com.genhao2.easycode.common.data.xss.core;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.IOException;

/**
 * jackson xss 处理
 *
 * @author luyanan
 * @since 2022/7/4
 **/
public abstract class XssCleanDeserializerBase extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		JsonToken jsonToken = p.getCurrentToken();
		if (JsonToken.VALUE_STRING != jsonToken) {
			throw MismatchedInputException.from(p, String.class, "Cannot deserialize value of type java.lang.String from " + jsonToken);
		}
		// 解析字符串
		String text = p.getValueAsString();
		if (text == null) {
			return null;
		}
		// xss 配置
		return this.clean(text);
	}

	/**
	 * 清理 xss
	 *
	 * @param text text
	 * @return String
	 * @throws IOException io异常
	 */
	public abstract String clean(String text) throws IOException;

}