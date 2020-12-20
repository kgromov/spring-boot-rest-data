package com.spring.templates.domain;

import org.springframework.data.rest.core.config.Projection;

/*@Projection(
        name = "notes",
        types = {Recipe.class})*/
public interface RecipeNotes {
    Notes getNotes();
}
