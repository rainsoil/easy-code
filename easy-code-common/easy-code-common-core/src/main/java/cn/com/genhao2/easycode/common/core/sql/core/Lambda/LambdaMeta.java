package cn.com.genhao2.easycode.common.core.sql.core.Lambda;

/**
 * Lambda 信息
 *
 * @author luyanan
 * @since 2022/11/24
 **/
public interface LambdaMeta {

	/**
	 * 获取 lambda 表达式实现方法的名称
	 *
	 * @return lambda 表达式对应的实现方法名称
	 */
	String getImplMethodName();

	/**
	 * 实例化该方法的类
	 *
	 * @return 返回对应的类名称
	 */
	Class<?> getInstantiatedClass();

}