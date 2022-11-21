package cn.com.genhao2.easycode.common.core.core;

import cn.com.genhao2.easycode.common.core.exception.BaseException;
import cn.com.genhao2.easycode.common.core.msg.GlobalMsgCode;
import lombok.Data;

import java.util.function.Supplier;

/**
 * 响应
 *
 * @param <T> 泛型
 * @author luyanan
 * @since 2022/9/14
 **/
@Data
public class R<T> {


	private static final Integer SUCCESS_STATUS = GlobalMsgCode.SUCCESS;
	private static final Integer ERROR_STATUS = GlobalMsgCode.INTERNAL_SERVER_ERRO;
	/**
	 * 返回信息
	 *
	 * @since 2022/9/14
	 */

	private String msg;
	/**
	 * 返回数据
	 *
	 * @since 2022/9/14
	 */

	private T data;
	/**
	 * 返回状态
	 *
	 * @since 2022/9/14
	 */

	private Integer status;

	/**
	 * 成功
	 *
	 * @param <T>  泛型
	 * @param data 数据
	 * @return cc.rainsoil.shop.core.core.R<T>
	 * @since 2022/9/14
	 */
	public static <T> R<T> ok(T data) {
		R r = new R();
		r.setStatus(SUCCESS_STATUS);
		r.setMsg("操作成功");
		r.setData(data);
		return r;
	}

	/**
	 * 断言是否为真，如果为 false 抛出给定的异常
	 *
	 * @param expression 表达式
	 * @param supplier   指定断言不通过时抛出的异常
	 * @param <T>        异常
	 * @param <X>        异常
	 * @return com.petecc.single.platform.common.core.R<T>
	 * @since 2022/10/20
	 */
	public static <T, X extends BaseException> R<T> isTrue(boolean expression, Supplier<? extends X> supplier) {
		if (expression) {
			return R.ok();
		} else {
			throw supplier.get();
		}
	}

	/**
	 * 断言是否为真，如果为 false 抛出给定的异常
	 *
	 * @param <T>        异常
	 * @param <X>        异常
	 * @param expression 表达式
	 * @param errorCode  不通过的时候跑出来的的异常值
	 * @return com.petecc.single.platform.common.core.R<T>
	 * @since 2022/10/20
	 */
	public static <T, X extends BaseException> R<T> isTrue(boolean expression, Integer errorCode) {
		if (expression) {
			return R.ok();
		} else {
			throw new BaseException(errorCode);
		}
	}

	/**
	 * 断言是否为真，如果为 false 抛出给定的异常
	 *
	 * @param expression 表达式
	 * @param <T>        异常
	 * @param <X>        异常
	 * @param errormsg   不通过的时候跑出来的的错误提示
	 * @return com.petecc.single.platform.common.core.R<T>
	 * @since 2022/10/20
	 */
	public static <T, X extends BaseException> R<T> isTrue(boolean expression, String errormsg) {
		if (expression) {
			return R.ok();
		} else {
			throw new BaseException(errormsg);
		}
	}

	/**
	 * 成功
	 *
	 * @param <T> 泛型
	 * @return cc.rainsoil.shop.core.core.R<T>
	 * @since 2022/9/14
	 */
	public static <T> R<T> ok() {
		R r = new R();
		r.setStatus(SUCCESS_STATUS);
		return r;
	}


	/**
	 * 失败
	 *
	 * @param <T> 结果泛型
	 * @return cn.com.genhao2.easycode.common.core.core.R<T>
	 * @since 2022/11/15
	 */
	public static <T> R<T> failed() {
		return restResult(null, ERROR_STATUS, null);
	}

	/**
	 * 失败
	 *
	 * @param <T> 结果泛型
	 * @param msg 失败消息
	 * @return cn.com.genhao2.easycode.common.core.core.R<T>
	 * @since 2022/11/15
	 */
	public static <T> R<T> failed(String msg) {
		return restResult(null, ERROR_STATUS, msg);
	}


	/**
	 * 失败
	 *
	 * @param <T>    泛型
	 * @param msg    错误信息
	 * @param status 状态码
	 * @return cc.rainsoil.shop.core.core.R
	 * @since 2022/9/14
	 */
	public static <T> R failed(Integer status, String msg) {
		R r = new R();
		r.setStatus(status);
		r.setMsg(msg);
		return r;
	}

	/**
	 * 设置返回结果
	 *
	 * @param data 数据
	 * @param code 编码
	 * @param msg  消息
	 * @param <T>  数据泛型
	 * @return cn.com.genhao2.easycode.common.core.core.R<T>
	 * @since 2022/11/15
	 */
	private static <T> R<T> restResult(T data, Integer code, String msg) {
		R<T> apiResult = new R<>();
		apiResult.setData(data);
		apiResult.setStatus(code);
		apiResult.setMsg(msg);
		return apiResult;
	}


	/**
	 * 检查返回是否成功,不成功则抛出异常
	 *
	 * @param exception 异常信息
	 * @since 2022/11/15
	 */
	public void check(BaseException exception) {
		if (!this.getStatus().equals(SUCCESS_STATUS)) {
			throw exception;
		}

	}
}
