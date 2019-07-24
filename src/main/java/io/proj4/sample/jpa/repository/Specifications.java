package io.proj4.sample.jpa.repository;

import java.util.Collection;

public class Specifications {
	private Specifications() {}
	
	public static <T> Specification<T> like(String field, String value) {
		return like(field, value, false);
	}
	
	public static <T> Specification<T> like(String field, String value, boolean ignoreCase) {
		return (root, builder) -> {
			if (ignoreCase) {
				return builder.like(builder.upper(root.get(field)), "%" + value.toUpperCase() + "%");
			}
			
			return builder.like(root.get(field), "%" + value + "%");
		};
	}
	
	public static <T, N extends Number> Specification<T> relational(
			String field, String operator, N value) {
		return (root, builder) -> {
			switch (operator) {
			case "=":
				return builder.equal(root.get(field), value);
				
			case "<>":
				return builder.notEqual(root.get(field), value);
			
			case ">":
				return builder.gt(root.get(field), value);
			
			case ">=":
				return builder.ge(root.get(field), value);
				
			case "<":
				return builder.lt(root.get(field), value);
				
			case "<=":
				return builder.le(root.get(field), value);

			default:
				throw new IllegalArgumentException("Invalid operator \"" + operator + "\"");
			}
		};
	}
	
	public static <T> Specification<T> in(String field, Collection<?> collection) {
		return (root, builder) -> root.get(field).in(collection);
	}
	
	public static <T> Specification<T> and(Specification<T> first, Specification<T> second) {
		return (root, builder) -> builder.and(
				first.toPredicate(root, builder),
				second.toPredicate(root, builder));
	}
	
	public static <T> Specification<T> or(Specification<T> first, Specification<T> second) {
		return (root, builder) -> builder.or(
				first.toPredicate(root, builder),
				second.toPredicate(root, builder));
	}
}
