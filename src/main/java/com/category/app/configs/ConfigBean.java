package com.category.app.configs;

import java.util.List;
import java.util.Map;

public class ConfigBean {
    private List<String> categories;
    private Map<String, String> strategies;

    public Map<String, String> getStrategies() {
        return strategies;
    }

    public void setStrategies(Map<String, String> strategies) {
        this.strategies = strategies;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
