package cn.com.genhao2.easycode.common.core.sql.core;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * SQL 保留关键字枚举
 *
 * @author luyanan
 * @since 2022/11/20
 **/
@AllArgsConstructor
public enum SqlKeyword implements ISqlSegment {

	AND(" AND "),
	OR("OR ({}) "),
	NOT("NOT"),
	IN("IN (?)"),
	NOT_IN("NOT IN (? )"),
	LIKE("LIKE concat('%','?','?')"),
	NOT_LIKE("NOT LIKE"),
	EQ(" {} = ? "),
	NE("<>"),
	GT(">"),
	GE(">="),
	LT("<"),
	LE("<="),
	IS_NULL(" {}  IS NULL "),
	IS_NOT_NULL("IS NOT NULL"),
	GROUP_BY("GROUP BY"),
	HAVING("HAVING"),
	ORDER_BY("ORDER BY"),
	EXISTS("EXISTS"),
	NOT_EXISTS("NOT EXISTS"),
	BETWEEN("BETWEEN"),
	NOT_BETWEEN("NOT BETWEEN"),
	ASC("ASC"),
	DESC("DESC"),
	LEFT_JOIN("LEFT JOIN {}  on {}"),
	RIGHT_JOIN("RIGHT JIIN"),
	SELECT("SELECT "),
	FROM(" FREOM "),
	WHERE(" WHERE "),
	;

	private final String keyword;

	@Override
	public String getSqlSegment() {
		return this.keyword;
	}

	@Override
	public List<Object> getSqlSegmentParam() {
		return null;
	}

}
