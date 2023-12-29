package com.core.repository

import com.core.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {
    suspend fun findByFirstName(name: String): User

    fun findAllByFirstNameStartsWith(name: String): User

}