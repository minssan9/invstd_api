package mongo.config.mongo

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableConfigurationProperties(MongoProperties::class)
@EnableMongoRepositories(
    basePackages = ["com.core.mongoRepo"],
    mongoTemplateRef = "mongoTemplate",
)
class MongoDbConfig(
    val mongoProperties: MongoProperties
) {
    fun mongoFactory(): SimpleMongoClientDatabaseFactory {
        // INFO: connectionString 기준의 연결은 authSource parameter를 반드시 필요로 한다.
        val connectionString = "mongodb://${mongoProperties.username}:${mongoProperties.password}@" +
                "${mongoProperties.host}:${mongoProperties.port}/product?authSource=admin"

        return SimpleMongoClientDatabaseFactory(connectionString)
    }

    @Bean("mongoTemplate", "secondMongoTemplate")
    fun mongoTemplate(): MongoTemplate = MongoTemplate(mongoFactory())
}