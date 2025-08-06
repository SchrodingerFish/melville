package com.cn.melville;

import com.cn.melville.config.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MelvilleApplication {

    public static void main(String[] args) {
        //加载环境变量
        EnvLoader.loadEnv(".env");
        SpringApplication.run(MelvilleApplication.class, args);
    }

}