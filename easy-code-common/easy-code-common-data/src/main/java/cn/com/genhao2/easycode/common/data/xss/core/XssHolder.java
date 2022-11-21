package cn.com.genhao2.easycode.common.data.xss.core;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * 利用 ThreadLocal 缓存线程间的数据
 *
 * @author luyanan
 * @since 2022/7/4
 **/

@UtilityClass
public class XssHolder {


	private static final ThreadLocal<Boolean> TL = new TransmittableThreadLocal<>();

	/**
	 * 是否开启
	 *
	 * @return boolean
	 * @since 2022/7/4
	 */
	public boolean isEnabled() {
		return Boolean.TRUE.equals(TL.get());
	}

	/**
	 * 设置开启
	 *
	 * @since 2022/7/4
	 */
	public void setEnable() {
		TL.set(Boolean.TRUE);
	}

	/**
	 * 移除
	 *
	 * @since 2022/7/4
	 */
	public void remove() {
		TL.remove();
	}
}
