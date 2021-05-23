package com.spring.templates.domain.restquery;

import com.spring.templates.domain.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class RecipeSpecification implements Specification<Recipe> {
    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperator().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperator().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperator().equalsIgnoreCase("~")) {
            return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperator().equalsIgnoreCase(":")) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        return null;
    }
}
