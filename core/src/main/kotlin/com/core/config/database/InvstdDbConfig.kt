package com.core.config.database

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "investandEntityManagerFactory",
    basePackages = ["com.core.repository"]
)
internal class InvstdDbConfig {

    @Primary
    @Bean(name = ["investandDataSourceProperties"])
    @ConfigurationProperties("db-investand")
    fun investandDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }


    @Primary
    @Bean(name = ["investandDataSource"])
    @ConfigurationProperties("db-investand.configuration")
    fun dataSource(@Qualifier("investandDataSourceProperties") investandDataSourceProperties: DataSourceProperties): DataSource {
        return investandDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
    }

    @Primary
    @Bean(name = ["investandEntityManagerFactory"])
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("investandDataSource") investandDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder
            .dataSource(investandDataSource)
            .packages("com.core.entity")
            .persistenceUnit("investand")
            .build()
    }

    @Primary
    @Bean(name = ["investandTransactionManager"])
    fun transactionManager(
        @Qualifier("investandEntityManagerFactory") investandEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(investandEntityManagerFactory)
    }
}
