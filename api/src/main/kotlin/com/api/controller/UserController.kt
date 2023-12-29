package com.api.controller

import com.core.component.exception.CommonException
import com.core.component.exception.CommonExceptionType
import com.core.entity.User
import com.core.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity as ResponseEntity

@CrossOrigin(origins = ["*"])
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
class UserController(
    private val userRepository: UserRepository
) {


    @GetMapping("/users")
    fun allUsers(): List<User> {
        return userRepository.findAll().toList()
    }


    @GetMapping("/users/{id}")
    @Throws(CommonException::class)
    fun getUserById(@PathVariable(value = "id") id: String): ResponseEntity<User> {
        val user: User = userRepository.findById(id)
            .orElseThrow { CommonException(CommonExceptionType.NOT_FOUND_MESSAGE, "User not found for this id :: $id") }
        return ResponseEntity.ok(user)
    }

    @PostMapping("/users")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok().body(userRepository.save(user))
    }

    @PutMapping("/users/{id}")
    @Throws(CommonException::class)
    fun updateUser(@PathVariable(value = "id") id: String, @RequestBody userDto: User): ResponseEntity<User> {
        val user: User = userRepository.findById(id)
            .orElseThrow { CommonException(CommonExceptionType.NOT_FOUND_MESSAGE, "User not found for this id :: $id") }

        user.emailId = userDto.emailId
        user.lastName = userDto.lastName
        user.firstName = userDto.firstName
        user.id = id
        val updateUser = userRepository.save(user)
        return ResponseEntity.ok(updateUser)
    }

    @DeleteMapping("/users/{id}")
    @Throws(CommonException::class)
    fun deleteUser(@PathVariable(value = "id") id: String): ResponseEntity<Boolean> {
        val user: User = userRepository.findById(id)
            .orElseThrow { CommonException(CommonExceptionType.NOT_FOUND_MESSAGE, "User not found for this id :: $id") }
        userRepository.delete(user)
        return ResponseEntity.ok(true)
    }
}
