package com.core.config.database

import lombok.Data
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties("db-investand")
@Data
@Component
class InvestandProperties {
    var ur1l: String = ""
    var username: String = ""
    var password: String = ""
    var driverClassName: String = ""
}
