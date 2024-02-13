package com.example.multidbspringboot.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager",
        /*basePackages = "com.subh.jpademo.entity.user"*/
        basePackages = "com.example.multidbspringboot.repository.user"
)
public class UserDbConfig {

    @Bean(name = "userProperties")
    @ConfigurationProperties("spring.datasource")
    @Primary
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }


    @Bean("userDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(@Qualifier("userProperties") DataSourceProperties dataSourceProperties){

        return dataSourceProperties.initializeDataSourceBuilder().build();

    }


    @Primary
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder factoryBuilder, @Qualifier("userDataSource") DataSource dataSource) {
        return factoryBuilder.dataSource(dataSource).packages("com.example.multidbspringboot.entity.user")
                .persistenceUnit("user").build();
    }


    @Bean(name = "userTransactionManager")
    @ConfigurationProperties("spring.jpa")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("userEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


}
