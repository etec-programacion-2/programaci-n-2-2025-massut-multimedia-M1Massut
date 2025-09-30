package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement


class Newreset (
    val id: String
    val titulo: String
    val note: String
    val duracion: Float
    val reguion: String
){
    constructor(id: String, titulo: String, note: String, duracion: Int, reguion: String) {
        this.id = id
        this.titulo = titulo
        this.note = note
        this.duracion = duracion
    }

    override fun toString(): String {
        return "Newreset(id='$id', titulo='$titulo', note=$note, duracion=$duracion, reguion=$reguion)"
    }

}