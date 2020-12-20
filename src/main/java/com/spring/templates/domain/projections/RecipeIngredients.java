package com.spring.templates.domain.projections;


import com.spring.templates.domain.Ingredient;
import com.spring.templates.domain.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(
        name = "ingredients",
        types = {Recipe.class})
public interface RecipeIngredients {
    @Value("#{target.ingredients.size()}")
    int getIngredientsCount();

    Set<Ingredient> getIngredients();
}
