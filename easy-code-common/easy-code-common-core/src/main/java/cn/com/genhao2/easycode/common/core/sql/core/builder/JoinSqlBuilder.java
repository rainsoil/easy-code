package cn.com.genhao2.easycode.common.core.sql.core.builder;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 拼接
 *
 * @author luyanan
 * @since 2022/11/20
 **/
public interface JoinSqlBuilder<Children> extends Serializable {

	Children or(boolean condition, Supplier<Children> supplier);

}
