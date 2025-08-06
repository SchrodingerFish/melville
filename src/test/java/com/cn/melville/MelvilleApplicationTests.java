package com.cn.melville;

import com.cn.melville.config.EnvLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MelvilleApplicationTests {


    @Test
    void contextLoads() {
        //加载环境变量
        EnvLoader.loadEnv(".env");
        // 打印从 .env 文件中加载的属性
        System.out.println("OpenAI URL: " + System.getProperty("OPENAI_API_KEY"));        // 这里可以添加更多的断言或验证逻辑来测试应用程序的功能
    }



}
