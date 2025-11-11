package org.example

/**
 * Contenedor sencillo para inyectar la implementación del repositorio.
 * Permite que el código dependa de la abstracción `RecetaRepository`.
 */
object AppContext {
    // Por defecto usamos DatabaseHelper; se puede sustituir en tests u otra configuración
    var repository: RecetaRepository = DatabaseHelper("data.db")
}
