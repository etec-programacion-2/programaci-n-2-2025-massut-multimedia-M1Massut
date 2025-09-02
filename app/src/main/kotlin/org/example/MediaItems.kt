package org.example.Clases

	abstract class ArchivoMultimedia(
		val id: String,
		val title: String,
		val tamaño: Long, // en bytes
		val filepath: String
	)
	class Audio(
		id: String,
		titulo: String,
		tamaño: Long,
		filepath: String,
		val duracion: Int // en segundos
	) : ArchivoMultimedia(id, titulo, tamaño, filepath) {
		override fun toString(): String = "Audio(id='$id', titulo='$titulo', tamaño=$tamaño, filepath='$filepath', duracion=$duracion)"
	}


	class Video(
		id: String,
		titulo: String,
		tamaño: Long,
		filepath: String,
		val duracion: Int, // en segundos
		val resolucion: String
	) : ArchivoMultimedia(id,titulo, tamaño, filepath) {
		override fun toString(): String = "Video(id='$id', title='$title', tamaño=$tamaño, filepath='$filepath', duracion=$duracion, resolucion='$resolucion')"
	}

	class Imagen(
		id: String,
		titulo: String,
		tamaño: Long,
		filepath: String,
		val resolucion: String
	) : ArchivoMultimedia(id, titulo, tamaño, filepath) {
		override fun toString(): String = "Imagen(id='$id', titulo='$titulo', tamaño=$tamaño, filepath='$filepath', resolucion='$resolucion')"
	}
