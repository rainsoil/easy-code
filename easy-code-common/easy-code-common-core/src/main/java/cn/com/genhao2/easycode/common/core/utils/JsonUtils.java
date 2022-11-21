package cn.com.genhao2.easycode.common.core.utils;

import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

/**
 * json工具类
 *
 * @author luyanan
 * @since 2022/10/13
 **/
@UtilityClass
public class JsonUtils {


	/**
	 * 对象转Json字符串
	 *
	 * @param obj 对象
	 * @return java.lang.String
	 * @since 2022/10/13
	 */
	public String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
}
