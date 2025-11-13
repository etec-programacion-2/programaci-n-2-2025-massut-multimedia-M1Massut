================================================================================
                        APLICACIÓN DE GESTIÓN DE RECETAS
================================================================================
                        Máximo Aznar Massut
================================================================================

*** COMANDOS PARA EJECUTAR LA APLICACIÓN ***

Paso N°1: Clonar el repositorio
    ┌───────────────────────────────────────────────────────────────────────────────────────────────┐
     git clone git@github.com:etec-programacion-2/programaci-n-2-2025-massut-multimedia-M1Massut.git
    └───────────────────────────────────────────────────────────────────────────────────────────────┘

Paso N°2: Navegar al directorio del proyecto
    ┌──────────────────────────────────────────────────┐
     cd programaci-n-2-2025-massut-multimedia-M1Massut                     
    └──────────────────────────────────────────────────┘

Paso N°3: Ejecutar la aplicación
    ┌──────────────┐
     ./gradlew run                                                       
    └──────────────┘

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
    Gradle              : 9.1.0

*** FUNCIONAMIENTO DE LOS ARCHIVOS ***

    App.kt
        └─> Clase principal ConsoleApp que ejecuta la aplicación en modo
            consola. Inicializa el repositorio y permite demostración de
            funcionalidades básicas sin interfaz gráfica.

    AppLauncher (dentro de App.kt)
         └─> Objeto singleton que contiene el método main() de la aplicación.
             Punto de entrada único que lanza la interfaz gráfica JavaFX
             mediante Application.launch().

    AppContext.kt
        └─> Contenedor de inyección de dependencias simple. Proporciona
            acceso global a la implementación del repositorio (DatabaseHelper)
            y permite sustituirla fácilmente para pruebas unitarias.
    
    RecetaRepository.kt
        └─> Interfaz que define el contrato para operaciones CRUD sobre
            recetas. Abstrae la lógica de persistencia permitiendo
            múltiples implementaciones (SQLite, memoria, API REST, etc.).


     DatabaseHelper.kt
        └─> Implementación concreta de RecetaRepository usando SQLite.
            Responsabilidades principales

    MenuInteractivo.kt
        └─> Gestión del menú interactivo en modo consola. Proporciona una
            interfaz de línea de comandos (CLI) alternativa para usuarios
            que prefieren trabajar sin entorno gráfico.

     Newrescet.kt
        └─> Define el modelo de dominio para recetas mediante clases

    RecipeApp.kt
        └─> Interfaz gráfica completa de la aplicación usando JavaFX.
            Implementa todas las ventanas y diálogos para gestionar

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

