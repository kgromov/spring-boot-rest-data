package com.spring.templates;

import com.spring.templates.domain.Category;
import com.spring.templates.domain.Recipe;
import com.spring.templates.fixtures.RecipeFixture;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataRestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringDataRelationshipsTest {
    private static final String RECIPES_ENDPOINT = "http://localhost:8080/recipes/";
    private static final String CATEGORIES_ENDPOINT = "http://localhost:8080/categories/";
    private static final String UNIT_OF_MEASSURES_ENDPOINT = "http://localhost:8080/unitOfMeassures/";

    @Autowired
    private TestRestTemplate template;

    @Test
    public void createCategory() {
        Category newCategory = new Category();
        newCategory.setDescription("Ukrainian Food");
        template.postForEntity(CATEGORIES_ENDPOINT, newCategory, Category.class);

        ResponseEntity<Category> responseEntity = template.getForEntity(CATEGORIES_ENDPOINT + "/1", Category.class);
        Category createdCategory = responseEntity.getBody();
        assertNotNull(createdCategory);
        assertEquals(createdCategory.getDescription(), createdCategory.getDescription());
    }

    @Test
    public void testRecipeCreate() {
        Recipe newRecipe = RecipeFixture.createRecipeWithNotes();

        template.postForEntity(RECIPES_ENDPOINT, newRecipe, Recipe.class);

        ResponseEntity<Recipe> recipeResponse = template.getForEntity(RECIPES_ENDPOINT + "/1", Recipe.class);
        Recipe createdRecipe = recipeResponse.getBody();
        assertNotNull(createdRecipe);
        assertEquals(createdRecipe.getDescription(), newRecipe.getDescription());
    }

    // FIXME
    @Test
    @Ignore
    public void testRecipeCreateWithCategory() {
        Recipe newRecipe = RecipeFixture.createRecipeWithNotes();
        template.postForEntity(RECIPES_ENDPOINT, newRecipe, Recipe.class);

        Category newCategory = new Category();
        newCategory.setDescription("Ukrainian Food");
        template.postForEntity(CATEGORIES_ENDPOINT, newCategory, Category.class);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/uri-list");
        HttpEntity<String> categoryEntity = new HttpEntity<>(CATEGORIES_ENDPOINT + "/1", requestHeaders);
        template.exchange(RECIPES_ENDPOINT + "/1/categories",
                HttpMethod.PUT, categoryEntity, String.class);

        ResponseEntity<Recipe> recipeResponse = template.getForEntity(RECIPES_ENDPOINT + "/1", Recipe.class);
        Recipe createdRecipe = recipeResponse.getBody();
        assertNotNull(createdRecipe);
        assertThat(createdRecipe.getCategories().size(), equalTo(1));
        assertThat(createdRecipe.getCategories().iterator().next(), equalTo(newCategory));
    }
}
