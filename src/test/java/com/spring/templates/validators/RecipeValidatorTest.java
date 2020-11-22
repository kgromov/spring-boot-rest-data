package com.spring.templates.validators;

import com.spring.templates.SpringDataRestApplication;
import com.spring.templates.domain.Recipe;
import com.spring.templates.fixtures.RecipeFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataRestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RecipeValidatorTest {
    private static final String RECIPES_ENDPOINT = "http://localhost:8080/recipes/";

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testRecipeCreate_emptyDescription() {
        Recipe newRecipe = new Recipe();
        newRecipe.setCookTime(10);

        ResponseEntity<Recipe> recipeResponse = template.postForEntity(RECIPES_ENDPOINT, newRecipe, Recipe.class);

        HttpStatus statusCode = recipeResponse.getStatusCode();
        HttpHeaders headers = recipeResponse.getHeaders();

        Recipe createdRecipe = recipeResponse.getBody();
        assertNull(createdRecipe);
    }

    @Test
    public void testRecipeCreate_negativeCookTime() {
        Recipe newRecipe = new Recipe();
        newRecipe.setDescription("Some recipe");
        newRecipe.setCookTime(-10);

        ResponseEntity<Object> recipeResponse = template.postForEntity(RECIPES_ENDPOINT, newRecipe, Object.class);

       /* HttpStatus statusCode = recipeResponse.getStatusCode();
        HttpHeaders headers = recipeResponse.getHeaders();

//        Recipe createdRecipe = recipeResponse.getBody();*/
//        assertNull(createdRecipe);
    }

    @Test
    public void testRecipeCreate_emptySource() {
        Recipe newRecipe = new Recipe();
        newRecipe.setDescription("Some recipe");
        newRecipe.setCookTime(10);

        ResponseEntity<Recipe> recipeResponse = template.postForEntity(RECIPES_ENDPOINT, newRecipe, Recipe.class);

        HttpStatus statusCode = recipeResponse.getStatusCode();
        HttpHeaders headers = recipeResponse.getHeaders();

        Recipe createdRecipe = recipeResponse.getBody();
        assertNull(createdRecipe);
    }
}