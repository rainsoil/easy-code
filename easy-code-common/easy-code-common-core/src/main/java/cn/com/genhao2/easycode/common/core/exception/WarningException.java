package cn.com.genhao2.easycode.common.core.exception;


import cn.com.genhao2.easycode.common.core.msg.GlobalMsgCode;

/**
 * 警告异常
 *
 * @author luyanan
 * @since 2022/10/13
 **/
public class WarningException extends RuntimeException {

	private String msg;

	private Integer code;

	public WarningException(Integer code) {
		super(code.toString());
		this.code = code;
		this.msg = code.toString();
	}


	public WarningException(String msg) {
		super(msg);
		this.code = GlobalMsgCode.INTERNAL_SERVER_ERRO;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}
}
