package com.spring.templates.controllers;

import com.spring.templates.domain.Recipe;
import com.spring.templates.domain.restquery.RecipeSpecification;
import com.spring.templates.domain.restquery.RecipeSpecificationBuilder;
import com.spring.templates.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController("recipes")
@RequiredArgsConstructor
public class RecipeSearchController {
    public static final Pattern PATTERN = Pattern.compile("(\\w+?)([:<>~])(\\w+?),");
    private final RecipeRepository recipeRepository;

    // e.g. ?search=ingredients=8
    @GetMapping
    public List<Recipe> search(@RequestParam("search") String search) {
        RecipeSpecificationBuilder builder = new RecipeSpecificationBuilder();
        Matcher matcher = PATTERN.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Recipe> specification = builder.build();
        return recipeRepository.findAll(specification);
    }
}
