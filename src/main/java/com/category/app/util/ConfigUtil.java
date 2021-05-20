package com.category.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.yaml.snakeyaml.Yaml;

import com.category.app.configs.ConfigBean;

public final class ConfigUtil {

    private ConfigUtil() {
    }

    public static ConfigBean getConfig(String filePath) {
        try (InputStream in = Files.newInputStream(Paths.get(filePath))) {
            return new Yaml().loadAs(in, ConfigBean.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ConfigBean();
        }
    }
}
