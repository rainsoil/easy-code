package cn.com.genhao2.easycode.common.core.core;


import lombok.Data;

/**
 * 分页请求
 *
 * @param <T> 数据泛型
 * @author luyanan
 * @since 2022/9/17
 **/
@Data
public class PageRequest<T> {

	private long current;
	private long size;
	private T param;

}
