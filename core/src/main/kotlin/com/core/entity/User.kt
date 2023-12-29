package com.core.entity

import jakarta.persistence.Table

@Table(name = "user")
data class User(
    var id: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var emailId: String? = null
) {
}
