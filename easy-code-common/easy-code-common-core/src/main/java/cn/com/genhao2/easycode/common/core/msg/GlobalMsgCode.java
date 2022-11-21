package cn.com.genhao2.easycode.common.core.msg;

/**
 * 消息编码
 *
 * @author luyanan
 * @since 2022/10/13
 **/

public interface GlobalMsgCode {

	/**
	 * 成功过
	 *
	 * @since 2022/10/13
	 */

	int SUCCESS = 200;

	/**
	 * 错误请求)
	 *
	 * @since 2022/10/13
	 */

	int BAD_REQUEST = 400;


	/**
	 * 未授权
	 *
	 * @since 2022/10/13
	 */

	int UNAUTHORIZED = 401;

	/**
	 * 禁止
	 *
	 * @since 2022/10/13
	 */

	int FORBIDDEN = 403;
	/**
	 * 未找到
	 *
	 * @since 2022/10/13
	 */

	int NOT_FOUND = 404;

	/**
	 * 方法未允许
	 *
	 * @since 2022/10/13
	 */

	int METHOD_NOT_ALLOWED = 405;


	/**
	 * 内部服务器错误
	 *
	 * @since 2022/10/13
	 */

	int INTERNAL_SERVER_ERRO = 500;


	/**
	 * 添加失败
	 *
	 * @since 2022/10/20
	 */

	int SAVE_FAIR = 600;


	/**
	 * 修改失败
	 *
	 * @since 2022/10/20
	 */

	int UPDATE_FAIL = 601;


	/**
	 * 删除失败
	 *
	 * @since 2022/10/20
	 */

	int DELETE_FAIL = 602;
}
