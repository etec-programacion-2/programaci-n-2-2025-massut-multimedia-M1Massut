================================================================================
                        APLICACIÓN DE GESTIÓN DE RECETAS
================================================================================
                        Máximo Aznar Massut
================================================================================

*** COMANDOS PARA EJECUTAR LA APLICACIÓN ***

Paso N°1: Clonar el repositorio
    ┌────────────────────────────────────────────────────────────────────────┐
    │ git clone git@github.com:etec-programacion-2/programaci-n-2-2025-     │
    │ massut-multimedia-M1Massut.git                                         │
    └────────────────────────────────────────────────────────────────────────┘

Paso N°2: Navegar al directorio del proyecto
    ┌────────────────────────────────────────────────────────────────────────┐
    │ cd programaci-n-2-2025-massut-multimedia-M1Massut                      │
    └────────────────────────────────────────────────────────────────────────┘

Paso N°3: Ejecutar la aplicación
    ┌────────────────────────────────────────────────────────────────────────┐
    │ ./gradlew run                                                          │
    └────────────────────────────────────────────────────────────────────────┘

*** FUNCIONAMIENTO DE LA APLICACIÓN ***

    Esta aplicación funciona como un libro de recetas contando con 6 botones
    con distintas funciones:

    [1] Agregar Receta
        └─> Permite agregar una nueva receta a la base de datos. Se utiliza
            una clase padre como base para la creación de nuevas recetas.
            Cada receta tiene:
            • ID: Generado automáticamente al crear la receta
            • Título: Nombre de la receta
            • Descripción: Instrucciones de preparación
            • Fecha de Creación: Se guarda automáticamente al crear la receta

    [2] Ver Todas las Recetas
        └─> Muestra todo el contenido almacenado en la base de datos de forma organizada y completa

    [3] Buscar Receta por ID
        └─> Permite buscar y visualizar una receta específica usando su ID

    [4] Actualizar Receta
        └─> Modifica la información de una receta existente en la basede datos

    [5] Eliminar Receta
        └─> Elimina permanentemente una receta de la base de datos

    [6] Salir
        └─> Cierra la aplicación de forma segura

 *** VERSIONES UTILIZADAS ***

    Java                : 17.0.16
    JavaFX              : 0.0.14
    Gradle              : 4.4.1
    SQLite              : (Base de datos embebida)

*** FUNCIONAMIENTO DE LOS ARCHIVOS ***

    App.java
        └─> Archivo principal donde también se encuentra la gestión de la
            base de datos SQLite. Punto de entrada de la aplicación.

    DatabaseHelper.java
        └─> Configuración, creación y funciones que se pueden realizar en
            la base de datos. Maneja todas las operaciones CRUD (Crear,
            Leer, Actualizar, Eliminar).

    MenuInteractivo.java
        └─> Gestión del menú interactivo. Controla la navegación y la
            interacción del usuario con las diferentes opciones.

    Newrecet.java
        └─> Clase padre para la creación de las nuevas recetas. Define
            la estructura base que todas las recetas deben seguir.

    RecipeApp.java
        └─> Interfaz gráfica de la aplicación. Implementa la UI usando
            JavaFX para una experiencia visual moderna.

*** ESTRUCTURA DEL PROYECTO ***

    programaci-n-2-2025-massut-multimedia-M1Massut/
    │
    ├── src/
    │   ├── App.java
    │   ├── DatabaseHelper.java
    │   ├── MenuInteractivo.java
    │   ├── Newrecet.java
    │   └── RecipeApp.java
    │
    ├── build.gradle
    ├── settings.gradle
    └── README.md

