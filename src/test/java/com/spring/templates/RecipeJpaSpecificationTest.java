package com.spring.templates;

import com.spring.templates.domain.Recipe;
import com.spring.templates.domain.restquery.RecipeSpecification;
import com.spring.templates.domain.restquery.SearchCriteria;
import com.spring.templates.fixtures.RecipeFixture;
import com.spring.templates.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { PersistenceJPAConfig.class })
@DataJpaTest
@Transactional
public class RecipeJpaSpecificationTest {
    @Autowired
    private RecipeRepository testee;

    // Perfect Guacamole
    private Recipe recipe1 = RecipeFixture.createRecipeWithNotes();
    private Recipe recipe2 = RecipeFixture.createRecipeWithNotes();

    @Before
    public void setUp() {
        recipe1.setSource("something");
        recipe2.setSource("something");
        recipe2.setDescription("Perfecto");
        testee.save(recipe1);
    }

    @Test
    public void testSearchCriteria_whenEqualsOperator_thenReturnPersistedObject() {
        SearchCriteria searchCriteria = new SearchCriteria("description", "Perfect Guacamole", ":");
        RecipeSpecification specification = new RecipeSpecification(searchCriteria);
        List<Recipe> results =  testee.findAll(Specification.where(specification));

        assertThat(recipe1, isIn(results));
    }

    @Test
    public void testSearchCriteria_whenEqualsOperator_thenReturnEmptyList() {
        SearchCriteria searchCriteria = new SearchCriteria("description", "Perfect", ":");
        RecipeSpecification specification = new RecipeSpecification(searchCriteria);
        List<Recipe> results =  testee.findAll(Specification.where(specification));

        assertThat(recipe1, not(isIn(results)));
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testSearchCriteria_whenSearchKeyIsNOtFound_thenThrowException() {
        SearchCriteria searchCriteria = new SearchCriteria("name", "Perfect Guacamole", ":");
        RecipeSpecification specification = new RecipeSpecification(searchCriteria);
        testee.findAll(Specification.where(specification));
    }

    @Test
    public void testSearchCriteria_whenSearchByTwoCriterias_thenReturnTwoRecipes() {
        SearchCriteria searchCriteria = new SearchCriteria("description", "Perfect", "~");
        SearchCriteria searchCriteria2 = new SearchCriteria("description", "Guacamole", "~");
        RecipeSpecification specification = new RecipeSpecification(searchCriteria);
        RecipeSpecification specification2 = new RecipeSpecification(searchCriteria2);
        List<Recipe> results =  testee.findAll(Specification.where(specification).or(specification2));

        assertThat(recipe1, not(isIn(results)));
        assertThat(recipe2, not(isIn(results)));
    }
}
