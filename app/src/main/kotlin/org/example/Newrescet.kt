package org.example

// Clase base para nuevas recetas y dos subclases (salada / dulce)
sealed class Newreset(
    open val id: String,
    open val titulo: String,
    open val note: String,
    open val duracion: Float,
    open val reguion: String,
    open val tipo: String
) {
    override fun toString(): String {
        return "Newreset(id='$id', titulo='$titulo', note=$note, duracion=$duracion, reguion=$reguion, tipo=$tipo)"
    }
}
//creación de clase hija de la clase Newreset rescetas saladas y dulces
data class RecetaSalada(
    override val id: String,
    override val titulo: String,
    override val note: String,
    override val duracion: Float,
    override val reguion: String,
    override val tipo: String = "salada"
) : Newreset(id, titulo, note, duracion, reguion, tipo)

data class RecetaDulce(
    override val id: String,
    override val titulo: String,
    override val note: String,
    override val duracion: Float,
    override val reguion: String,
    override val tipo: String = "dulce"
) : Newreset(id, titulo, note, duracion, reguion, tipo)
