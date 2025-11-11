package org.example
//importaciones 
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

//creación de la clase DatabaseHelper 
class DatabaseHelper(private val dbPath: String = "data.db") : RecetaRepository {
    
    private fun connect(): Connection {
        return DriverManager.getConnection("jdbc:sqlite:$dbPath")
    }
    
    // Crear la tabla de recetas si no existe
    override fun createTable() {
        val sql = """
            CREATE TABLE IF NOT EXISTS recetas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                descripcion TEXT,
                tipo TEXT DEFAULT 'salada',
                fecha_creacion TEXT DEFAULT CURRENT_TIMESTAMP
            );
        """.trimIndent()
        
        connect().use { conn ->
            conn.createStatement().use { stmt ->
                stmt.execute(sql)
                println("✓ Tabla 'recetas' creada/verificada correctamente")
            }
            // Si la tabla ya existía y no tiene la columna 'tipo', intentar agregarla (se ignora si ya existe)
            try {
                conn.createStatement().use { stmt ->
                    stmt.execute("ALTER TABLE recetas ADD COLUMN tipo TEXT DEFAULT 'salada';")
                }
            } catch (e: Exception) {
                // columna ya existe u otro error — ignoramos para compatibilidad
            }
        }
    }
    
    // Insertar una nueva receta
    override fun insertarReceta(nombre: String, descripcion: String, tipo: String): Int {
        val sql = "INSERT INTO recetas (nombre, descripcion, tipo) VALUES (?, ?, ?)"
        
        connect().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, nombre)
                pstmt.setString(2, descripcion)
                pstmt.setString(3, tipo)
                pstmt.executeUpdate()
            }
            
            // Obtener el último ID insertado
            conn.createStatement().use { stmt ->
                val rs = stmt.executeQuery("SELECT last_insert_rowid()")
                if (rs.next()) {
                    val id = rs.getInt(1)
                    println("✓ Receta '$nombre' guardada con ID: $id")
                    return id
                }
            }
        }
        return -1
    }
    
    // Obtener todas las recetas
    override fun obtenerTodasLasRecetas(): List<Receta> {
        val recetas = mutableListOf<Receta>()
        val sql = "SELECT id, nombre, descripcion, tipo, fecha_creacion FROM recetas"
        
        connect().use { conn ->
            conn.createStatement().use { stmt ->
                val rs: ResultSet = stmt.executeQuery(sql)
                while (rs.next()) {
                    recetas.add(
                        Receta(
                            id = rs.getInt("id"),
                            nombre = rs.getString("nombre"),
                            descripcion = rs.getString("descripcion") ?: "",
                            tipo = rs.getString("tipo") ?: "",
                            fechaCreacion = rs.getString("fecha_creacion")
                        )
                    )
                }
            }
        }
        return recetas
    }
    
    // Buscar receta por ID
    override fun buscarRecetaPorId(id: Int): Receta? {
        val sql = "SELECT id, nombre, descripcion, tipo, fecha_creacion FROM recetas WHERE id = ?"
        
        connect().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setInt(1, id)
                val rs = pstmt.executeQuery()
                
                if (rs.next()) {
                    return Receta(
                        id = rs.getInt("id"),
                        nombre = rs.getString("nombre"),
                        descripcion = rs.getString("descripcion") ?: "",
                        tipo = rs.getString("tipo") ?: "",
                        fechaCreacion = rs.getString("fecha_creacion")
                    )
                }
            }
        }
        return null
    }
    
    // Actualizar receta
    override fun actualizarReceta(id: Int, nombre: String, descripcion: String, tipo: String): Boolean {
        val sql = "UPDATE recetas SET nombre = ?, descripcion = ?, tipo = ? WHERE id = ?"
        
        connect().use { conn ->
            conn.prepareStatement(sql).use { pstmt ->
                pstmt.setString(1, nombre)
                pstmt.setString(2, descripcion)
                pstmt.setString(3, tipo)
                pstmt.setInt(4, id)
                
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
    override fun eliminarReceta(id: Int): Boolean {
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
    val tipo: String,
    val fechaCreacion: String
) {
    override fun toString(): String {
        return "Receta(id=$id, nombre='$nombre', tipo='$tipo', descripcion='$descripcion', fecha='$fechaCreacion')"
    }
}

