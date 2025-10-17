
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

fun menuInteractivo(db: DatabaseHelper) {
    println("\n\n=== Menú Interactivo ===")
    println("1. Agregar receta")
    println("2. Ver todas las recetas")
    println("3. Buscar receta por ID")
    println("4. Actualizar receta")
    println("5. Eliminar receta")
    println("0. Salir")
    
    print("\nSeleccione una opción: ")
    when (readLine()?.toIntOrNull()) {
        1 -> {
            print("Nombre de la receta: ")
            val nombre = readLine() ?: ""
            print("Descripción: ")
            val desc = readLine() ?: ""
            db.insertarReceta(nombre, desc)
            menuInteractivo(db)
        }
        2 -> {
            println("\n--- Lista de Recetas ---")
            db.obtenerTodasLasRecetas().forEach { println(it) }
            menuInteractivo(db)
        }
        3 -> {
            print("ID de la receta: ")
            val id = readLine()?.toIntOrNull() ?: 0
            val receta = db.buscarRecetaPorId(id)
            if (receta != null) {
                println("Encontrada: $receta")
            } else {
                println("❌ Receta no encontrada")
            }
            menuInteractivo(db)
        }
        4 -> {
            print("ID de la receta a actualizar: ")
            val id = readLine()?.toIntOrNull() ?: 0
            print("Nuevo nombre: ")
            val nombre = readLine() ?: ""
            print("Nueva descripción: ")
            val desc = readLine() ?: ""
            db.actualizarReceta(id, nombre, desc)
            menuInteractivo(db)
        }
        5 -> {
            print("ID de la receta a eliminar: ")
            val id = readLine()?.toIntOrNull() ?: 0
            db.eliminarReceta(id)
            menuInteractivo(db)
        }
        0 -> println("¡Hasta luego!")
        else -> {
            println("❌ Opción inválida")
            menuInteractivo(db)
        }
    }
}