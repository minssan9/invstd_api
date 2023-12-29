package mongo.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "user")
data class User(
    var id: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var emailId: String? = null
) {
}
