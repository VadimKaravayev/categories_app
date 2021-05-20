package com.category.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.category.app.configs.ConfigBean;
import com.category.app.exceptions.NoSuchCategoryException;
import com.category.app.strategies.print.CategoryPrintStrategy;
import com.category.app.util.ConfigUtil;
import com.category.app.util.FileUtil;
import com.category.app.util.ReflectUtil;

public class CategoryApp {

    private final Map<String, List<String>> categoryStore = new HashMap<>();
    private final Map<String, CategoryPrintStrategy> strategies = new HashMap<>();

    public CategoryApp(String configFile) {
        configure(configFile);
    }

    private void configure(String configFile) {
        ConfigBean config = ConfigUtil.getConfig(configFile);
        List<String> categories = config.getCategories();
        categories.forEach(cat -> categoryStore.put(cat, new ArrayList<>()));
        Map<String, String> mappedStrategies = config.getStrategies();
        mappedStrategies.forEach((key, value) -> strategies.put(key, ReflectUtil.getObjectFromString(value)));
    }

    public void loadData(String filePath) throws IOException {
        List<String> lines = FileUtil.readLinesFromFile(filePath);
        processData(lines);
    }

    private void processData(List<String> lines) {
        categoryStore.forEach((key, value) -> value.clear());
        String currentCategory = StringUtils.EMPTY;
        for (String line : lines) {
            currentCategory = Optional.ofNullable(line)
                    .map(String::toLowerCase)
                    .filter(categoryStore::containsKey)
                    .orElse(currentCategory);
            categoryStore.computeIfPresent(currentCategory, (key, value) -> Optional.ofNullable(line)
                    .filter(el -> !Objects.equals(el.toLowerCase(), key))
                    .map(el -> Stream.concat(value.stream(), Stream.of(el))
                            .collect(Collectors.toList()))
                    .orElse(value));
        }
    }

    public Map<String, List<String>> getProcessedData() {
        return Collections.unmodifiableMap(categoryStore);
    }

    public void addCategory(String category) {
        categoryStore.put(category.toLowerCase(), new ArrayList<>());
    }

    public void removeCategory(String category) {
        categoryStore.remove(category);
    }

    public List<String> getAllCategories() {
        return new ArrayList<>(categoryStore.keySet());
    }

    public void printCategory(String category) throws NoSuchCategoryException {
            List<String> list = Optional.ofNullable(category)
                    .map(String::toLowerCase)
                    .map(categoryStore::get)
                    .orElseThrow(() -> new NoSuchCategoryException("Category: " + category + " does not exist"));
            Optional.ofNullable(strategies.get(category.toLowerCase()))
                    .orElse(data -> data.forEach(System.out::println))
                    .printCategory(list);
    }

    public void addPrintStrategy(String category, CategoryPrintStrategy strategy) {
        strategies.put(category.toLowerCase(), strategy);
    }

    public void removePrintStrategy(String category) {
        strategies.remove(category);
    }
}
