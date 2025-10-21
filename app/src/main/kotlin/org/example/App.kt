
/*
 * Sistema de Gestión de Recetas
 * Autor: Máximo Aznar Massut
 */
package org.example

fun main() {
    println("=== Sistema de Gestión de Recetas ===\n")
    
    // Inicializar base de datos
    val db = DatabaseHelper("data.db")
    db.createTable()

    // Ejemplo de uso: Insertar recetas
    /* 
    //println("\n--- Insertando recetas ---")
    val id1 = db.insertarReceta("Pizza Margherita", "Pizza clásica italiana con tomate, mozzarella y albahaca")
    val id2 = db.insertarReceta("Empanadas Argentinas", "Empanadas rellenas de carne, cebolla y especias")
    val id3 = db.insertarReceta("Medialunas", "Medialunas dulces argentinas para el desayuno")
    */
    val id1 = 1
    val id2 = 2 
    val id3 = 3
    // Listar todas las recetas
    println("\n--- Todas las recetas ---")
    val recetas = db.obtenerTodasLasRecetas()
    recetas.forEach { println(it) }
    
    // Buscar una receta específica
    println("\n--- Buscar receta por ID ---")
    val receta = db.buscarRecetaPorId(id2)
    if (receta != null) {
        println("Encontrada: $receta")
    } else {
        println("Receta no encontrada")
    }
    
    // Actualizar una receta
    println("\n--- Actualizando receta ---")
    db.actualizarReceta(
        id1, 
        "Pizza Margherita Premium", 
        "Pizza con tomate San Marzano, mozzarella di bufala y albahaca fresca"
    )
    
    // Listar recetas actualizadas
    println("\n--- Recetas actualizadas ---")
    db.obtenerTodasLasRecetas().forEach { println(it) }
    
    // Menú interactivo (opcional)
    menuInteractivo(db)
}