package com.spring.templates.handlers;

import com.spring.templates.domain.Category;
import com.spring.templates.domain.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler(Category.class)
public class CategoryEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryEventHandler.class);

    @HandleBeforeCreate
    public void handleBookBeforeCreate(Category category){
        LOGGER.info("Inside Category Before Create ....");
    }

    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(Recipe recipe){
        LOGGER.info("Inside Recipe After Create ....");
    }

}
