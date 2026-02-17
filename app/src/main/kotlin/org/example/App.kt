
/*
 * Sistema de Gestión de Recetas
 * Autor: Máximo Aznar Massut
 */
package org.example
import javafx.application.Application


class ConsoleApp(private val repo: RecetaRepository = AppContext.repository) { //ejecutar la aplicación 
    fun run() {
        println("=== Sistema de Gestión de Recetas ===\n")
        repo.createTable()
        // Ejemplos y demostración ligera (no invoked automatically)
        println("\n--- Todas las recetas ---")
        val recetas = repo.obtenerTodasLasRecetas()
        recetas.forEach { println(it) }

        // Menú interactivo opcional
        // val menu = MenuInteractivo(); menu.run(repo)
    }
}

// Objeto que expone el único punto de entrada `main` de la aplicación (máx 10 líneas)
object AppLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        Application.launch(RecipeApp::class.java)
    }
}