package org.example // Interfaz contrato para el repositorio de recetas esta debe proporcionar una implementación de los miembros definidos en la interfaz.

interface RecetaRepository {
    fun createTable()
    fun insertarReceta(nombre: String, descripcion: String, tipo: String): Int
    fun obtenerTodasLasRecetas(): List<Receta>
    fun buscarRecetaPorId(id: Int): Receta?
    fun actualizarReceta(id: Int, nombre: String, descripcion: String, tipo: String): Boolean
    fun eliminarReceta(id: Int): Boolean
}
