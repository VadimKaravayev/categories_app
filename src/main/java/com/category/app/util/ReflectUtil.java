package com.category.app.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.category.app.strategies.print.CategoryPrintStrategy;

public final class ReflectUtil {
    public ReflectUtil() {
    }

    public static CategoryPrintStrategy getObjectFromString(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> constructor = aClass.getConstructors()[0];
            Object o = constructor.newInstance();
            return (CategoryPrintStrategy) o;
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
