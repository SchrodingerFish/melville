package com.cn.melville.config;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {

    public static void loadEnv(String filePath) {
        File envFile = new File(filePath);

        if (envFile.exists() && envFile.isFile()) {
            try (FileInputStream fileInputStream = new FileInputStream(envFile)) {
                Properties properties = new Properties();
                properties.load(fileInputStream);

                // 将属性加载到系统属性中
                properties.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));

                System.out.println(".env properties loaded successfully: " + properties);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load .env file", e);
            }
        } else {
            throw new RuntimeException(".env file not found at: " + filePath);
        }
    }
}
