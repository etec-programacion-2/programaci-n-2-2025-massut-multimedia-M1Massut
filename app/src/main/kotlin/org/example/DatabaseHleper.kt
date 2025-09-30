package org.example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class DatabaseHelper(private val dbPath: String = "data.db") {
    
    private fun connect(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:$dbPath")
    }
    
    // Crear la tabla de recetas si no existe
    fun createTable() {
        val sql = """
            CREATE TABLE IF NOT EXISTS recetas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                descripcion TEXT,
                fecha_creacion TEXT DEFAULT CURRENT_TIMESTAMP
            );
        """.trimIndent()
        
        connect().use { conn ->
            conn.createStatement().use { stmt ->
                stmt.execute(sql)
                println("✓ Tabla 'recetas' creada/verificada correctamente")
            }
        }
    }
    
    // Insertar una nueva receta
    fun insertarReceta(nombre: String, descripcion: String = ""): Int {
        val sql = "INSERT INTO recetas (nombre, descripcion) VALUES (?, ?)"
        
        connect().use { conn ->
            conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS).use { pstmt ->
                pstmt.setString(1, nombre)
                pstmt.setString(2, descripcion)
                pstmt.executeUpdate()
                
                val generatedKeys = pstmt.generatedKeys
                if (generatedKeys.next()) {
                    val id = generatedKeys.getInt(1)
                    println("✓ Receta '$nombre' guardada con ID: $id")
                    return id
                }
            }
        }
        return -1
    }
    
    // Obtener todas las recetas
    fun obtenerTodasLasRecetas(): List<Receta> {
        val recetas = mutableListOf<Receta>()
        val sql = "SELECT id, nombre, descripcion, fecha_creacion FROM recetas"
        
        connect().use { conn ->
            conn.createStatement().use { stmt ->
                val rs: ResultSet = stmt.executeQuery(sql)
                while (rs.next()) {
                    recetas.add(
                        Receta(
                            id = rs.getInt("id"),
                            nombre = rs.getString("nombre"),
                            descripcion = rs.getString("descripcion"),
                            fechaCreacion = rs.getString("fecha_creacion")
                        )
                    )
                }
            }
        }
        return recetas
    }
    
    // Buscar receta por ID
    fun buscarRecetaPorId(id: Int): Receta? {
        val sql = "SELECT id, nombre, descripcion, fecha_creacion FROM recetas WHERE id = ?"
        
        connect().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                val rs = pstmt.executeQuery()
                
                if (rs.next()) {
                    return Receta(
                        id = rs.getInt("id"),
                        nombre = rs.getString("nombre"),
                        descripcion = rs.getString("descripcion"),
                        fechaCreacion = rs.getString("fecha_creacion")
                    )
                }
            }
        }
        return null
    }
    
    // Actualizar receta
    fun actualizarReceta(id: Int, nombre: String, descripcion: String): Boolean {
        val sql = "UPDATE recetas SET nombre = ?, descripcion = ? WHERE id = ?"
        
        connect().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, nombre)
                pstmt.setString(2, descripcion)
                pstmt.setInt(3, id)
                
                val rowsAffected = pstmt.executeUpdate()
                if (rowsAffected > 0) {
                    println("✓ Receta ID $id actualizada")
                    return true
                }
            }
        }
        return false
    }
    
    // Eliminar receta
    fun eliminarReceta(id: Int): Boolean {
        val sql = "DELETE FROM recetas WHERE id = ?"
        
        connect().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                
                val rowsAffected = pstmt.executeUpdate()
                if (rowsAffected > 0) {
                    println("✓ Receta ID $id eliminada")
                    return true
                }
            }
        }
        return false
    }
}

// Data class para representar una receta
data class Receta(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val fechaCreacion: String
) {
    override fun toString(): String {
        return "Receta(id=$id, nombre='$nombre', descripcion='$descripcion', fecha='$fechaCreacion')"
    }
}