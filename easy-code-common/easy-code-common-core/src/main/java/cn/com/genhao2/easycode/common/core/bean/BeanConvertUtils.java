package cn.com.genhao2.easycode.common.core.bean;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * bean 转换
 *
 * @author luyanan
 * @since 2022/7/3
 **/
@UtilityClass
public class BeanConvertUtils extends BeanUtils {
	/**
	 * Bean属性复制工具方法。
	 *
	 * @param dest 目标对象
	 * @param src  源对象
	 */
	public static void copyProperties(Object dest, Object src) {
		try {
			BeanUtils.copyProperties(src, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 转换
	 *
	 * @param source         源对象
	 * @param targetSupplier 目标对象供应方
	 * @param <S>            源对象泛型
	 * @param <T>            目标对象泛型
	 * @return T 目标对象类型
	 * @since 2022/7/3
	 */
	public <S, T> T convertTo(S source, Supplier<T> targetSupplier) {
		return convertTo(source, targetSupplier, null);
	}

	/**
	 * 转换对象
	 *
	 * @param source         源对象
	 * @param targetSupplier 目标对象供应方
	 * @param callBack       回调方法
	 * @param <S>            源对象类型
	 * @param <T>            目标对象类型
	 * @return 目标对象
	 */
	public <S, T> T convertTo(S source, Supplier<T> targetSupplier, ConvertCallBack<S, T> callBack) {
		if (null == source || null == targetSupplier) {
			return null;
		}

		T target = targetSupplier.get();
		BeanUtils.copyProperties(source, target);
		if (callBack != null) {
			callBack.callBack(source, target);
		}
		return target;
	}

	/**
	 * list 转换
	 *
	 * @param sources        源对象
	 * @param targetSupplier 目标对象供应方
	 * @param <S>            源对象类型
	 * @param <T>            目标对象类型
	 * @return java.util.List<T>  返回结果
	 * @since 2022/7/3
	 */
	public <S, T> List<T> convertListTo(List<S> sources, Supplier<T> targetSupplier) {
		return convertListTo(sources, targetSupplier, null);
	}

	/**
	 * 转换对象
	 *
	 * @param sources        源对象list
	 * @param targetSupplier 目标对象供应方
	 * @param callBack       回调方法
	 * @param <S>            源对象类型
	 * @param <T>            目标对象类型
	 * @return 目标对象list
	 */
	public <S, T> List<T> convertListTo(List<S> sources, Supplier<T> targetSupplier, ConvertCallBack<S, T> callBack) {
		if (null == sources || null == targetSupplier) {
			return null;
		}

		List<T> list = new ArrayList<>(sources.size());
		for (S source : sources) {
			T target = targetSupplier.get();
			BeanUtils.copyProperties(source, target);
			if (callBack != null) {
				callBack.callBack(source, target);
			}
			list.add(target);
		}
		return list;
	}

//    /**
//     * 回调接口
//     *
//     * @param <S> 源对象类型
//     * @param <T> 目标对象类型
//     */
//    @FunctionalInterface
//    public interface ConvertCallBack<S, T> {
//        /**
//         * 回调方法
//         *
//         * @param t 目标对象类型
//         * @param s 源对象类型
//         * @since 2022/7/3
//         */
//        void callBack(S t, T s);
//    }
}