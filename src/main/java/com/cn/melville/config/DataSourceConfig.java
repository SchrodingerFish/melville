package com.cn.melville.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // Primary PostgreSQL DataSource Configuration
    @Bean(name = "primaryPgDataSourceProperties")
    @Primary // Designate as default datasource
    @ConfigurationProperties("spring.datasource.primary-pg")
    public DataSourceProperties primaryPgDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "primaryPgDataSource")
    @Primary
    public DataSource primaryPgDataSource(@Qualifier("primaryPgDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "primaryPgJdbcTemplate")
    @Primary
    public JdbcTemplate primaryPgJdbcTemplate(@Qualifier("primaryPgDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "primaryPgTransactionManager")
    @Primary
    public PlatformTransactionManager primaryPgTransactionManager(@Qualifier("primaryPgDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // Secondary PostgreSQL DataSource Configuration
    @Bean(name = "secondaryPgDataSourceProperties")
    @ConfigurationProperties("spring.datasource.secondary-pg")
    public DataSourceProperties secondaryPgDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "secondaryPgDataSource")
    public DataSource secondaryPgDataSource(@Qualifier("secondaryPgDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "secondaryPgJdbcTemplate")
    public JdbcTemplate secondaryPgJdbcTemplate(@Qualifier("secondaryPgDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "secondaryPgTransactionManager")
    public PlatformTransactionManager secondaryPgTransactionManager(@Qualifier("secondaryPgDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // SQLite DataSource Configuration
    @Bean(name = "sqliteDataSourceProperties")
    @ConfigurationProperties("spring.datasource.sqlite")
    public DataSourceProperties sqliteDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "sqliteDataSource")
    public DataSource sqliteDataSource(@Qualifier("sqliteDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "sqliteJdbcTemplate")
    public JdbcTemplate sqliteJdbcTemplate(@Qualifier("sqliteDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "sqliteTransactionManager")
    public PlatformTransactionManager sqliteTransactionManager(@Qualifier("sqliteDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // MySQL DataSource Configuration
    @Bean(name = "mysqlDataSourceProperties")
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource(@Qualifier("mysqlDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
