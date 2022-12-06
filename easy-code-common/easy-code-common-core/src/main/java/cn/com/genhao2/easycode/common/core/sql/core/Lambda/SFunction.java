package cn.com.genhao2.easycode.common.core.sql.core.Lambda;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 支持序列化的 Function
 *
 * @author luyanan
 * @since 2022/11/24
 **/
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {

}
