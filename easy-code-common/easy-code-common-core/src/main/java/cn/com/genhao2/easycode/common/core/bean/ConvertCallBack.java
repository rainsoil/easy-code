package cn.com.genhao2.easycode.common.core.bean;

/**
 * 转换回调
 *
 * @param <S> 源对象类型泛型
 * @param <T> 目标对象类型泛型
 * @author luyanan
 * @since 2022/10/20
 **/
@FunctionalInterface
public interface ConvertCallBack<S, T> {
	/**
	 * 回调方法
	 *
	 * @param t 目标对象类型
	 * @param s 源对象类型
	 * @since 2022/7/3
	 */
	void callBack(S t, T s);
}
