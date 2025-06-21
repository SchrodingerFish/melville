package com.cn.melville.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class TestService {

    @Autowired
    @Qualifier("primaryPgJdbcTemplate")
    private JdbcTemplate primaryPgJdbcTemplate;
    @Autowired
    @Qualifier("secondaryPgJdbcTemplate")
    private JdbcTemplate secondaryPgJdbcTemplate;
    @Autowired
    @Qualifier("sqliteJdbcTemplate")
    private JdbcTemplate sqliteJdbcTemplate;
    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate mysqlJdbcTemplate;

    @Test
    public void doSomething() {
        // 使用不同的 JdbcTemplate 操作不同的数据库
        String version = primaryPgJdbcTemplate.queryForObject("select version()", String.class);
        String version2 = secondaryPgJdbcTemplate.queryForObject("select 1", String.class);
        String version3 = sqliteJdbcTemplate.queryForObject("select 1", String.class);
        String version4 = mysqlJdbcTemplate.queryForObject("select 1", String.class);
        // Process the data as needed
        System.out.println("Primary PG Data: " + version);
        System.out.println("Secondary PG Data: " + version2);
        System.out.println("SQLite Data: " + version3);
        System.out.println("MySQL Data: " + version4);
    }
}
