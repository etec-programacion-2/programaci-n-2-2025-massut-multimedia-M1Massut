package org.example

class Newreset {
    val id: String
    val titulo: String
    val tamaño: Int
    val filepath: String
    val duracion: Int

    constructor(id: String, titulo: String, tamaño: Int, filepath: String, duracion: Int) {
        this.id = id
        this.titulo = titulo
        this.tamaño = tamaño
        this.filepath = filepath
        this.duracion = duracion
    }

    override fun toString(): String {
        return "Newreset(id='$id', titulo='$titulo', tamaño=$tamaño, filepath='$filepath', duracion=$duracion)"
    }
}