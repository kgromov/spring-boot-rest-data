package com.spring.templates.config;

import com.spring.templates.domain.projections.RecipeIngredients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * To implement Projection (part of entity) as separate endpoint:
 * 1) add interface with getter of desired field type from entity (@Projection annotation);
 * 2) add some unique name within entity projections name (name attribute);
 * 3) point to entity type(s) - types attribute;
 * 4) declared interface should be in the same package as entity or registered directly
 *  in RepositoryRestConfigurer implementation component
 * 5) data could be calculated (even not present in original entity) via Spell
 * 6) another related entity could be included either
 */
@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getProjectionConfiguration().addProjection(RecipeIngredients.class);
    }
}
