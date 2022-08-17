package com.zovra.automation.automationapi.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import com.zovra.automation.automationapi.model.PowerConsumption.Log;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "powerConsumptionEntityManager",
        transactionManagerRef = "powerConsumptionTransactionManager",
        basePackages = "com.zovra.automation.automationapi.powerconsumption.dao"
)
public class PowerConsumptionDBConfig {
//    @Primary
//    @Bean
//    @ConfigurationProperties(prefix = "spring.powerconsumption.datasource")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder
//                .create()
//                .build();
//    }
    @Bean
    @Primary
    @ConfigurationProperties("spring.powerconsumption.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.powerconsumption.datasource")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }
    @Primary
    @Bean(name = "powerConsumptionEntityManager")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource(dataSourceProperties()))
                .properties(hibernateProperties())
                .packages(Log.class)
                .persistenceUnit("powerConsumptionPU")
                .build();
    }
    @Primary
    @Bean(name = "powerConsumptionTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("powerConsumptionEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map hibernateProperties() {

        Resource resource = new ClassPathResource("hibernate.properties");

        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);

            return properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey().toString(),
                            e -> e.getValue())
                    );
        } catch (IOException e) {
            return new HashMap();
        }
    }

}
