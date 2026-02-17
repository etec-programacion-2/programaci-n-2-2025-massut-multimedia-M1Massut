package org.example.repository

import org.example.model.User

interface UserRepository {
    fun create(receta: Receta)
    fun read(id: Int): Receta?
    fun readAll(): List<Receta>
    fun update(id: Int, user: Receta)
    fun delete(id: Int)
}