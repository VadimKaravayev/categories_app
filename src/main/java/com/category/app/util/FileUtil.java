package com.category.app.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class FileUtil {

    private FileUtil() {
    }

    public static List<String> readLinesFromFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }
}
