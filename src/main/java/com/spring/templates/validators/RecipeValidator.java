package com.spring.templates.validators;

import com.spring.templates.domain.Recipe;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// The same as in MVC - old java class
// hibernate/jpa validators on fields level work as well
/*
 * During every call on Spring Data REST API, Spring Data REST exporter generates various events which are listed here:
 * BeforeCreateEvent
 * AfterCreateEvent
 * BeforeSaveEvent
 * AfterSaveEvent
 * BeforeLinkSaveEvent
 * AfterLinkSaveEvent
 * BeforeDeleteEvent
 * AfterDeleteEvent
 *
 * TO bound validator to such event:
 * 1) @Component("beforeCreate<ValidatorClassName>");
 * 2) Separate bean:
    @Bean
	public WebsiteUserValidator beforeCreateWebsiteUserValidator() {
	    return new WebsiteUserValidator();
	}
 * 3) Override RepositoryRestConfigurerAdapter in any @Configuration or Application directly:
     @Override
	 public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
	       v.addValidator("beforeCreate", new WebsiteUserValidator());
	 }
 */
@Component("beforeCreateRecipeValidator")
public class RecipeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Recipe.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Recipe recipe = (Recipe) o;

        if (StringUtils.isEmpty(recipe.getDescription())) {
            errors.rejectValue("description", "description.empty",
                    "Unable to create recipe with empty description");
        }
        if (recipe.getCookTime() == null || recipe.getCookTime() < 0) {
            errors.rejectValue("cookTime", "cookTime.empty",
                    "Unable to create recipe with null or negative cook time");
        }
    }
}
