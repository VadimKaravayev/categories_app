package com.category.app.strategies.print.impl;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.category.app.strategies.print.CategoryPrintStrategy;

public class CarsPrintStrategy implements CategoryPrintStrategy {
    @Override
    public void printCategory(List<String> data) {
        data.stream()
                .map(String::toLowerCase)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .map(el -> el + "(" + DigestUtils.md5Hex(el) + ")")
                .forEach(System.out::println);
    }
}
