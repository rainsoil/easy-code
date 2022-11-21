package cn.com.genhao2.easycode.common.core.exception;


import cn.com.genhao2.easycode.common.core.msg.GlobalMsgCode;

/**
 * 错误异常
 *
 * @author luyanan
 * @since 2022/9/17
 **/
public class ErrorException extends RuntimeException {

	private String msg;

	private Integer code;

	public ErrorException(Integer code) {
		super(code.toString());
		this.code = code;
		this.msg = code.toString();
	}


	public ErrorException(String msg) {
		super(msg);
		this.code = GlobalMsgCode.INTERNAL_SERVER_ERRO;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}
}
