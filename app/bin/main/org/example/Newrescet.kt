package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement


class Newreset (
    val id: String,
    val titulo: String,
    val note: String,
    val duracion: Float,
    val reguion: String,
)
{
    override fun toString(): String {
        return "Newreset(id='$id', titulo='$titulo', note=$note, duracion=$duracion, reguion=$reguion)"
    }
}
