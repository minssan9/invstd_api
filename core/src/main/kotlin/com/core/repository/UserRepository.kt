package com.core.repository

import com.core.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserRepository : JpaRepository<User, String> {
    suspend fun findByFirstName(name: String): User

    fun findAllByFirstNameStartsWith(name: String): User

}