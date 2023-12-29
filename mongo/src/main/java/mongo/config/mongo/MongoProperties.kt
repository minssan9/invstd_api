package mongo.config.mongo

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "spring.data.mongodb")
data class MongoProperties(
    val host: String,
    val port: String,
    val username: String,
    val password: String
)
