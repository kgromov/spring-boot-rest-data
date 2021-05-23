package com.spring.templates.domain.restquery;

import com.spring.templates.domain.Recipe;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO: add abstract builder with generic
public class RecipeSpecificationBuilder {
    private final List<SearchCriteria> params = new ArrayList<>();

    public RecipeSpecificationBuilder with(String key, String operator, Object value) {
        params.add(new SearchCriteria(key, value, operator));
        return this;
    }

    public Specification<Recipe> build() {
        if (params.isEmpty()) {
            return null;
        }

        List<Specification<Recipe>> specs = params.stream()
                .map(RecipeSpecification::new)
                .collect(Collectors.toList());

        Specification<Recipe> result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(specs.get(i))
                    : Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}
