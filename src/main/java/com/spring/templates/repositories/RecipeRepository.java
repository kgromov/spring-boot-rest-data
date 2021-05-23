package com.spring.templates.repositories;

import com.spring.templates.domain.Recipe;
import com.spring.templates.domain.RecipeNotes;
import com.spring.templates.domain.projections.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by jt on 6/13/17.
 */
//@RepositoryRestResource(collectionResourceRel = "recipes", path = "recipes")
// excerptProjection is used to set default view for collection inside entity
@RepositoryRestResource(excerptProjection = RecipeIngredients.class)
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
}
