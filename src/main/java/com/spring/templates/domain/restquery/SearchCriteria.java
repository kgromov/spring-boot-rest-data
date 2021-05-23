package com.spring.templates.domain.restquery;

import lombok.Data;

@Data
public class SearchCriteria {
    private final String key;
    private final Object value;
    private final String operator;

    private boolean orPredicate;
}
