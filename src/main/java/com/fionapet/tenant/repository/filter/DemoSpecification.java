package com.fionapet.tenant.repository.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.fionapet.tenant.domain.Demo;
import org.springframework.data.jpa.domain.Specification;

public class DemoSpecification implements Specification<Demo> {

	private static final long serialVersionUID = 1L;
	
	private DemoFilter filter;

    public static DemoSpecification build(DemoFilter filter) {
        DemoSpecification demoSpecification = new DemoSpecification();
        demoSpecification.filter = filter;
        return demoSpecification;
    }

    @Override
    public Predicate toPredicate(Root<Demo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter != null) {
            if (filter.getDescription() != null) {
            	predicates.add(criteriaBuilder.like(root.get("description"), "%" + filter.getDescription() + "%"));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
