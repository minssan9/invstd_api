package com.core.config.database

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class InvestandDatabaseConfig(private val  investandProperties:InvestandProperties ) {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val url = investandProperties.url
        val username = investandProperties.username
        val password = investandProperties.password

        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "mysql")
                .option(ConnectionFactoryOptions.HOST, url)
                .option(ConnectionFactoryOptions.USER, username)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .option(ConnectionFactoryOptions.DATABASE, "investand")
                .build()
        )
    }
}
