//package cn.com.genhao2.easycode.common.core.sql.core.builder;
//
//import cn.com.genhao2.easycode.common.core.sql.core.SqlKeyword;
//import cn.com.genhao2.easycode.common.core.sql.core.Wrapper;
//import cn.com.genhao2.easycode.common.core.sql.core.segments.MergeSegments;
//import cn.com.genhao2.easycode.common.core.sql.core.segments.WhereSegmentList;
//import cn.hutool.core.lang.Opt;
//import com.sun.org.apache.regexp.internal.RE;
//
//import java.util.List;
//
///**
// * where条件构建
// *
// * @author luyanan
// * @since 2022/11/22
// **/
//public abstract class WhereQueryWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> extends Wrapper<T> implements
//		CompareSqlBuilder<Children, R> {
//	private MergeSegments mergeSegments = new MergeSegments();
//
//	/**
//	 * sql片段
//	 *
//	 * @return java.lang.String
//	 * @since 2022/11/19
//	 */
//	@Override
//	public String getSqlSegment() {
//		WhereSegmentList where = mergeSegments.getWhere();
//		return Opt.ofBlankAble(where.getSqlSegment())
//				.map(a -> SqlKeyword.WHERE.getSqlSegment()
//						+ where.getSqlSegment()).orElse("");
//	}
//
//	/**
//	 * sql参数
//	 *
//	 * @return java.util.List<java.lang.Object>
//	 * @since 2022/11/20
//	 */
//	@Override
//	public List<Object> getSqlSegmentParam() {
//		return mergeSegments.getSqlSegmentParam();
//	}
//
//
//	/**
//	 * 等于 =
//	 *
//	 * @param condition 执行条件
//	 * @param column    字段
//	 * @param val       值
//	 * @return children
//	 */
//	@Override
//	public Children eq(boolean condition, R column, Object val) {
//		return null;
//	}
//
//
//	protected abstract Children typedThis();
//
//}
