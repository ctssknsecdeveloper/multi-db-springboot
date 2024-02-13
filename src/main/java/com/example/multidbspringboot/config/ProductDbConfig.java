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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        /*basePackages = "com.subh.jpademo.entity.user"*/
        basePackages = "com.example.multidbspringboot.repository.product"
)
public class ProductDbConfig {

    @Bean(name = "productProperties")
    @ConfigurationProperties("spring.datasource.product")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }


    @Bean("productDataSource")
    //@Primary
    @ConfigurationProperties("spring.datasource.product")
    public DataSource dataSource(@Qualifier("productProperties") DataSourceProperties dataSourceProperties){

        return dataSourceProperties.initializeDataSourceBuilder().build();

    }


    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    //@Primary
    @Bean(name="productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("productDataSource") DataSource dataSource)
    {
        return builder.dataSource(dataSource)
                .packages("com.example.multidbspringboot.entity.product")
                .persistenceUnit("product").build();
    }


    /*@Primary
    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder factoryBuilder, @Qualifier("productDataSource") DataSource dataSource) {
        return factoryBuilder.dataSource(dataSource).packages("com.example.multidbspringboot.entity.product")
                .persistenceUnit("product").build();
    }*/


    @Bean(name = "productTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(@Qualifier("productEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
