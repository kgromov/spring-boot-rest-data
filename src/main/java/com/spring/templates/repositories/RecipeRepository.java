package com.spring.templates.repositories;

import com.spring.templates.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 6/13/17.
 */
//@RepositoryRestResource(collectionResourceRel = "recipes", path = "recipes")
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
