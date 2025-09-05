package org.example.class

data class User (
    val id: String,
    val nombre: String,
    val email: String,
    val password: String,
    val playlists: MutableList<Playlist> = mutableListOf()
)
// se utiliza dataclass porque es una forma concisa de crear clases que son solo contenedores de datos.

