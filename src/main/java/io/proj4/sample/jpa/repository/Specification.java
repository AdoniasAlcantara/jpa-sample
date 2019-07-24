package io.proj4.sample.jpa.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface Specification<T> {
	Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder);
}
