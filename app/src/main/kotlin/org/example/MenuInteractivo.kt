package org.example

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