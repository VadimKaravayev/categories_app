package com.category.app.strategies.print.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.category.app.strategies.print.CategoryPrintStrategy;

public class NumbersPrintStrategy implements CategoryPrintStrategy {
    @Override
    public void printCategory(List<String> data) {
        data.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
