package com.example.test1.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan("com.example.test1.*")
public class AppConfig {
    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("3366");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test_db?useUnicode=true&serverTimezone=UTC");
        return hikariConfig;
    }

    @Bean
    public HikariDataSource hikariDataSource() {
        return new HikariDataSource(hikariConfig());
    }


}

//public class AppConfig{
//    @Bean
//    public DataSource dataSource() {
//        HikariConfig config = new HikariConfig();
//        config.setUsername("root");
//        config.setPassword("3366");
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/test_db?useUnicode=true&serverTimezone=UTC");
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.addDataSourceProperty("cachePrepStatements", cachePrepStatements);
//        config.addDataSourceProperty("prepStmtCacheSize", prepStmtCacheSize);
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", prepStmtCacheSqlLimit);
//        return new HikariDataSource(config);
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManager =
//                new LocalContainerEntityManagerFactoryBean();
//        entityManager.setDataSource(dataSource());
//        entityManager.setPackagesToScan("com.mjc.entity");
//        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        entityManager.setJpaVendorAdapter(adapter);
//        entityManager.setJpaProperties(hibernateProperties());
//        return entityManager;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
//
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", dialect);
//        properties.put("hibernate.show_sql", showSql);
//        properties.put("hibernate.hbm2ddl.auto", autoCreateTable);
//        return properties;
//    }
//}
