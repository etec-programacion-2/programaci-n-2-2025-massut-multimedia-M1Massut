package org.example

import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.stage.Stage

//clase principal de la aplicación de recetas con interfaz gráfica
class RecipeApp : Application() {
    //interacción con la base de datos 
    private val db = DatabaseHelper("data.db")
    
    //función principal que inicia la aplicación
    override fun start(primaryStage: Stage) {
        // Inicializar base de datos
        db.createTable()
        
        primaryStage.title = "Gestor de Recetas"
        primaryStage.scene = createMainScene(primaryStage)
        primaryStage.show()
    }
    //creación de la ventana principal con botones estiliados y funciones asociadas
    private fun createMainScene(stage: Stage): Scene {
        // Título
        val titulo = Label("Gestor de Recetas").apply {
            font = Font.font("Arial", FontWeight.BOLD, 28.0)
            style = "-fx-text-fill: #2c3e50;"
        }
        
        // creaciones de los botones 
        val btnAgregar = createStyledButton("➕ Agregar Receta")
        val btnVerTodas = createStyledButton("📋 Ver Todas las Recetas")
        val btnBuscar = createStyledButton("🔍 Buscar Receta por ID")
        val btnActualizar = createStyledButton("✏️ Actualizar Receta")
        val btnEliminar = createStyledButton("🗑️ Eliminar Receta")
        val btnSalir = createStyledButton("❌ Salir").apply {
            style = "-fx-background-color: #922216ff; -fx-text-fill: white; " +
                    "-fx-font-size: 14px; -fx-background-radius: 5; -fx-cursor: hand;"
        }
        
        // se les da las funciones a cada botón 
        btnAgregar.setOnAction { mostrarFormularioAgregar(stage) }
        btnVerTodas.setOnAction { mostrarTodasRecetas(stage) }
        btnBuscar.setOnAction { mostrarBuscarReceta(stage) }
        btnActualizar.setOnAction { mostrarActualizarReceta(stage) }
        btnEliminar.setOnAction { mostrarEliminarReceta(stage) }
        btnSalir.setOnAction { stage.close() }
        
        // Layout
        val vbox = VBox(15.0).apply {
            padding = Insets(30.0)
            alignment = Pos.CENTER
            children.addAll(titulo, btnAgregar, btnVerTodas, btnBuscar, 
                           btnActualizar, btnEliminar, btnSalir)
        }
        
        return Scene(vbox, 450.0, 600.0)
    }
    //estilo de los botones
    private fun createStyledButton(text: String): Button {
        return Button(text).apply {
            prefWidth = 280.0
            prefHeight = 50.0
            style = "-fx-background-color: #186091ff; -fx-text-fill: white; " +
                    "-fx-font-size: 14px; -fx-background-radius: 5; -fx-cursor: hand;"
            
            setOnMouseEntered { 
                style = "-fx-background-color: #2980b9; -fx-text-fill: white; " +
                        "-fx-font-size: 14px; -fx-background-radius: 5; -fx-cursor: hand;"
            }
            setOnMouseExited { 
                style = "-fx-background-color: #348adbff; -fx-text-fill: white; " +
                        "-fx-font-size: 14px; -fx-background-radius: 5; -fx-cursor: hand;"
            }
        }
    }

    // ventana para Agregar Receta
    private fun mostrarFormularioAgregar(parentStage: Stage) {
        val dialog = Stage()
        dialog.title = "Agregar Nueva Receta"
        
        val lblNombre = Label("Nombre de la receta:")
        val txtNombre = TextField()
        
        val lblDescripcion = Label("Descripción:")
        val txtDescripcion = TextArea().apply {
            prefRowCount = 5
            isWrapText = true
        }
        
        val btnGuardar = Button("Guardar").apply {
            style = "-fx-background-color: #27ae60; -fx-text-fill: white;"
        }
        val btnCancelar = Button("Cancelar").apply {
            style = "-fx-background-color: #f00b0bff; -fx-text-fill: white;"
        }
        
        btnGuardar.setOnAction {
            val nombre = txtNombre.text.trim()
            val descripcion = txtDescripcion.text.trim()
            
            if (nombre.isNotEmpty()) {
                db.insertarReceta(nombre, descripcion)
                mostrarAlerta("Éxito", "Receta guardada correctamente", Alert.AlertType.INFORMATION)
                dialog.close()
            } else {
                mostrarAlerta("Error", "El nombre no puede estar vacío", Alert.AlertType.ERROR)
            }
        }
        
        btnCancelar.setOnAction { dialog.close() }
        
        val hbox = HBox(10.0, btnGuardar, btnCancelar).apply {
            alignment = Pos.CENTER
        }
        
        val vbox = VBox(10.0, lblNombre, txtNombre, lblDescripcion, txtDescripcion, hbox).apply {
            padding = Insets(20.0)
        }
        
        dialog.scene = Scene(vbox, 400.0, 300.0)
        dialog.show()
    }
    
    // ventana para Ver Todas las Recetas
    private fun mostrarTodasRecetas(parentStage: Stage) {
        val dialog = Stage()
        dialog.title = "Todas las Recetas"
        
        val recetas = db.obtenerTodasLasRecetas()
        val textArea = TextArea().apply {
            isEditable = false
            if (recetas.isEmpty()) {
                text = "No hay recetas guardadas"
            } else {
                text = recetas.joinToString("\n\n") { 
                    "ID: ${it.id}\nNombre: ${it.nombre}\nDescripción: ${it.descripcion}\nFecha: ${it.fechaCreacion}"
                }
            }
        }
        
        val btnCerrar = Button("Cerrar").apply {
            setOnAction { dialog.close() }
        }
        
        val vbox = VBox(10.0, textArea, btnCerrar).apply {
            padding = Insets(20.0)
            alignment = Pos.CENTER
        }
        
        dialog.scene = Scene(vbox, 500.0, 400.0)
        dialog.show()
    }

    // ventana para Buscar Receta
    private fun mostrarBuscarReceta(parentStage: Stage) {
        val dialog = Stage()
        dialog.title = "Buscar Receta por ID"
        
        val lblId = Label("ID de la receta:")
        val txtId = TextField()
        val btnBuscar = Button("Buscar")
        val resultArea = TextArea().apply {
            isEditable = false
            prefRowCount = 8
        }
        
        btnBuscar.setOnAction {
            val id = txtId.text.toIntOrNull()
            if (id != null) {
                val receta = db.buscarRecetaPorId(id)
                resultArea.text = if (receta != null) {
                    "ID: ${receta.id}\nNombre: ${receta.nombre}\nDescripción: ${receta.descripcion}\nFecha: ${receta.fechaCreacion}"
                } else {
                    "❌ Receta no encontrada"
                }
            } else {
                mostrarAlerta("Error", "Ingrese un ID válido", Alert.AlertType.ERROR)
            }
        }
        
        val vbox = VBox(10.0, lblId, txtId, btnBuscar, resultArea).apply {
            padding = Insets(20.0)
        }
        
        dialog.scene = Scene(vbox, 400.0, 350.0)
        dialog.show()
    }

    // ventana para Actualizar Receta
    private fun mostrarActualizarReceta(parentStage: Stage) {
        val dialog = Stage()
        dialog.title = "Actualizar Receta"
        
        val lblId = Label("ID de la receta:")
        val txtId = TextField()
        val lblNombre = Label("Nuevo nombre:")
        val txtNombre = TextField()
        val lblDesc = Label("Nueva descripción:")
        val txtDesc = TextArea().apply { prefRowCount = 4 }
        
        val btnActualizar = Button("Actualizar").apply {
            style = "-fx-background-color: #f39c12; -fx-text-fill: white;"
        }
        
        btnActualizar.setOnAction {
            val id = txtId.text.toIntOrNull()
            if (id != null && txtNombre.text.isNotEmpty()) {
                val success = db.actualizarReceta(id, txtNombre.text, txtDesc.text)
                if (success) {
                    mostrarAlerta("Éxito", "Receta actualizada", Alert.AlertType.INFORMATION)
                    dialog.close()
                } else {
                    mostrarAlerta("Error", "No se encontró la receta", Alert.AlertType.ERROR)
                }
            } else {
                mostrarAlerta("Error", "Complete todos los campos correctamente", Alert.AlertType.ERROR)
            }
        }
        
        val vbox = VBox(10.0, lblId, txtId, lblNombre, txtNombre, lblDesc, txtDesc, btnActualizar).apply {
            padding = Insets(20.0)
        }
        
        dialog.scene = Scene(vbox, 400.0, 400.0)
        dialog.show()
    }

    // ventana para Eliminar Receta
    private fun mostrarEliminarReceta(parentStage: Stage) {
        val dialog = Stage()
        dialog.title = "Eliminar Receta"
        
        val lblId = Label("ID de la receta a eliminar:")
        val txtId = TextField()
        val btnEliminar = Button("Eliminar").apply {
            style = "-fx-background-color: #e74c3c; -fx-text-fill: white;"
        }
        
        btnEliminar.setOnAction {
            val id = txtId.text.toIntOrNull()
            if (id != null) {
                val alert = Alert(Alert.AlertType.CONFIRMATION)
                alert.title = "Confirmar eliminación"
                alert.headerText = "¿Está seguro de eliminar la receta ID $id?"
                alert.contentText = "Esta acción no se puede deshacer"
                
                val result = alert.showAndWait()
                if (result.isPresent && result.get() == ButtonType.OK) {
                    val success = db.eliminarReceta(id)
                    if (success) {
                        mostrarAlerta("Éxito", "Receta eliminada", Alert.AlertType.INFORMATION)
                        dialog.close()
                    } else {
                        mostrarAlerta("Error", "No se encontró la receta", Alert.AlertType.ERROR)
                    }
                }
            } else {
                mostrarAlerta("Error", "Ingrese un ID válido", Alert.AlertType.ERROR)
            }
        }
        
        val vbox = VBox(15.0, lblId, txtId, btnEliminar).apply {
            padding = Insets(20.0)
            alignment = Pos.CENTER
        }
        
        dialog.scene = Scene(vbox, 350.0, 200.0)
        dialog.show()
    }
    
    private fun mostrarAlerta(titulo: String, mensaje: String, tipo: Alert.AlertType) {
        val alert = Alert(tipo)
        alert.title = titulo
        alert.headerText = null
        alert.contentText = mensaje
        alert.showAndWait()
    }
}
// llamada a la función main para que inicie la aplicación
fun main() {
    Application.launch(RecipeApp::class.java)
}