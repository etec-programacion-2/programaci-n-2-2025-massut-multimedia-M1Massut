package org.example.Clases

data class Media(
    val id: String,
    val titulo: String,
    val tamaño: Int,
    val creador: User,
    val mediaList: MutableList<Media> = mutableListOf()
)
