package org.example.Clases

	abstract class ArchivoMultimedia(
		val id: String,
		val title: String,
		val tamaño: Long, // en bytes
		val filepath: String
	)
	class Audio(
		id: String,
		title: String,
		tamaño: Long,
		filepath: String,
		val duracion: Int // en segundos
	) : ArchivoMultimedia(id, title, tamaño, filepath) {
		override fun toString(): String = "Audio(id='$id', title='$title', tamaño=$tamaño, filepath='$filepath', duracion=$duracion)"
	}


	class Video(
		id: String,
		title: String,
		tamaño: Long,
		filepath: String,
		val duracion: Int, // en segundos
		val resolucion: String
	) : ArchivoMultimedia(id,title, tamaño, filepath) {
		override fun toString(): String = "Video(id='$id', title='$title', tamaño=$tamaño, filepath='$filepath', duracion=$duracion, resolucion='$resolucion')"
	}

	class Imagen(
		id: String,
		title: String,
		tamaño: Long,
		filepath: String,
		val resolucion: String
	) : ArchivoMultimedia(id, title, tamaño, filepath) {
		override fun toString(): String = "Imagen(id='$id', title='$title', tamaño=$tamaño, filepath='$filepath', resolucion='$resolucion')"
	}
