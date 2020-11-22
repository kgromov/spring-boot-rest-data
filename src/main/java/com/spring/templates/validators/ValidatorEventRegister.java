package com.spring.templates.validators;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Workaround for not registering validators https://jira.spring.io/browse/DATAREST-524
@Configuration
public class ValidatorEventRegister implements InitializingBean {
    public static final List<String> DATA_REST_EVENTS = Arrays.asList("beforeCreate");

    @Autowired
    private ValidatingRepositoryEventListener validatingRepositoryEventListener;

    @Autowired
    private Map<String, Validator> validators;

    @Override
    public void afterPropertiesSet() throws Exception {
        validators.forEach((name, validator) ->
                DATA_REST_EVENTS.stream()
                        .filter(name::startsWith)
                        .forEach(eventName -> validatingRepositoryEventListener.addValidator(eventName, validator))
        );
    }
}
