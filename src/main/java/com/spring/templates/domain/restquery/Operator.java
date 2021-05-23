package com.spring.templates.domain.restquery;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
public enum Operator {
    EQUAL(":"),
    GRATER(">"),
    LOWER("<=>"),
    LIKE("~");

    private static final Map<String, Operator> OPERATORS = Stream.of(values())
            .collect(toMap(Operator::getValue, Function.identity()));

    @Getter
    private String value;

    public static Operator of(String code) {
        return Optional.ofNullable(OPERATORS.get(code))
                .orElseThrow(() -> new IllegalArgumentException("Not supported operator = " + code));
    }
}
