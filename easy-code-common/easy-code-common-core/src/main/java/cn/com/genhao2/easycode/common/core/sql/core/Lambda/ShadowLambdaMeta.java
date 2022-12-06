package cn.com.genhao2.easycode.common.core.sql.core.Lambda;

import cn.hutool.core.util.ClassUtil;


/**
 * @author luyanan
 * @since 2022/11/24
 **/
public class ShadowLambdaMeta implements LambdaMeta {
	private final SerializedLambda lambda;

	public ShadowLambdaMeta(SerializedLambda lambda) {
		this.lambda = lambda;
	}

	@Override
	public String getImplMethodName() {
		return lambda.getImplMethodName();
	}

	@Override
	public Class<?> getInstantiatedClass() {
		String instantiatedMethodType = lambda.getInstantiatedMethodType();
		String instantiatedType = instantiatedMethodType.substring(2, instantiatedMethodType.indexOf(";")).replace("\\",".");
		return  ClassUtil.loadClass(instantiatedType,true);
//		return ClassUtils.toClassConfident(instantiatedType, lambda.getCapturingClass().getClassLoader());
	}

}
