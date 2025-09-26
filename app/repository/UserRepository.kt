package org.example.repository

import org.example.model.User

interface UserRepository {
    fun create(user: User)
    fun read(id: Int): User?
    fun readAll(): List<User>
    fun update(id: Int, user: User)
    fun delete(id: Int)
}