
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
	) : ArchivoMultimedia(id, title, tamaño, filepath)


	class Video(
		id: String,
		title: String,
		tamaño: Long,
		filepath: String,
		val duracion: Int, // en segundos
		val resolucion: String
	) : ArchivoMultimedia(id,title, tamaño, filepath)

	class Imagen(
		id: String,
		title: String,
		tamaño: Long,
		filepath: String,
		val resolucion: String
	) : ArchivoMultimedia(id, title, tamaño, filepath)
