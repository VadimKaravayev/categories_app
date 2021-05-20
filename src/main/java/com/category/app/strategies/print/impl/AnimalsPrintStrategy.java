package com.category.app.strategies.print.impl;

import java.util.List;

import com.category.app.strategies.print.CategoryPrintStrategy;

public class AnimalsPrintStrategy implements CategoryPrintStrategy {
    @Override
    public void printCategory(List<String> data) {
        data.stream()
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}
