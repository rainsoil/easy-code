package cn.com.genhao2.easycode.common.core.exception;


import cn.com.genhao2.easycode.common.core.msg.GlobalMsgCode;

/**
 * 基础异常
 *
 * @author luyanan
 * @since 2022/9/17
 **/
public class BaseException extends RuntimeException {

	private String msg;

	private Integer code;

	public BaseException(Integer code) {
		super(code.toString());
		this.code = code;
		this.msg = code.toString();
	}


	public BaseException(String msg) {
		super(msg);
		this.code = GlobalMsgCode.INTERNAL_SERVER_ERRO;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}
}
