package cn.com.genhao2.easycode.common.core.bean;

import java.util.List;

/**
 * 列表转换回调
 *
 * @param <S> 源对象类型泛型
 * @param <T> 目标对象类型泛型
 * @author luyanan
 * @since 2022/10/20
 **/
@FunctionalInterface
public interface ListConvertCallBack<S, T> {
	/**
	 * 回调方法
	 *
	 * @param ts 目标对象类型
	 * @param ss 源对象类型
	 * @since 2022/7/3
	 */
	void callBack(List<S> ss, List<T> ts);
}
