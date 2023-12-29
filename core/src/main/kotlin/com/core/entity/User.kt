package com.core.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Id
    var id: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var emailId: String? = null
) {
}
