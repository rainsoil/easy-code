package cn.com.genhao2.easycode.common.core.spi;

/**
 * 对象存储
 *
 * @param <T> 扩展类泛型
 * @author luyanan
 * @since 2022/11/19
 **/
public class Holder<T> {


	/**
	 * 存储的值
	 *
	 * @since 2022/11/19
	 */

	private volatile T value;

	/**
	 * 获取值
	 *
	 * @return T 对象
	 * @since 2022/11/19
	 */
	public T get() {
		return value;
	}


	/**
	 * 设置值
	 *
	 * @param value 值
	 * @since 2022/11/19
	 */
	public void set(T value) {
		this.value = value;
	}
}
