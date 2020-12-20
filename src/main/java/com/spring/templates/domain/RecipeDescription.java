package com.spring.templates.domain;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "recipeDescription",
        types = {Recipe.class})
public interface RecipeDescription {
    @Value("#{target.id}")
    long getId();

    String getDescription();
}
