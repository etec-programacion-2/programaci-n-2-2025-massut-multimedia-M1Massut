Máximo Aznar Massut 

******************************** Comandos para que funcióne el codigo ******************************

Paso N°1
    git clone git@github.com:etec-programacion-2/programaci-n-2-2025-massut-multimedia-M1Massut.git

Paso N°2
    cd programaci-n-2-2025-massut-multimedia-M1Massut
Paso N°3
    ./gradlew run
************************************ Como funcióna la aplicación ***********************************

    Esta aplicación funcióna como un librio de recetas contando con 5 botones con distintas funciónes estos 6 son: 1 Agregar Receta, 2 Ver todas las recetas, 3 Buscar receta por ID, 4 Actualizar Receta, 5 Eliminar Receta y por ultimo un boton para salir de la aplicación.
    El botón Agregar Receta le agrege una clase padre para utilizarlo de bace para las siguientes recetas a crear Teniendo un ID dado automaticamente al crear el producto, un titulo, una descripcion y se guarda tambien la fecha creada la receta. 
    El botón Ver toda la receta te muestra el contenido de la bace de datos. Y despues los proximos botones son funciónes guardad dentro de esos botones.

******************************************** Versiones ********************************************

    Java: 17.0.16
    JavaFX: 0.0.14
    Gradle: 4.4.1

********************************** Funcionamiento de los archivos *********************************
App : Archivo principal donde tabien se encuentra la gestion de la base de datos de SQLite
DatabaseHelper : Configuración, creación y funciónes que se pueden hacer en la base de datos 
MenuInteractivo : Gestion del menú 
Newrecet : Clase padre para la creación de las nuevas recetas 
RecipeApp :Interfaz de la aplicación 

