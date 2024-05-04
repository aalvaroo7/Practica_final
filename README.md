https://github.com/aalvaroo7/Practica_final.git

La práctica consiste en desarrollar una aplicación de escritorio que permita a los biólogos gestionar cultivos de bacterias. La aplicación debe permitir crear, eliminar y visualizar experimentos y poblaciones de bacterias, así como calcular la dosis de comida para cada día del experimento.
Estructura del Proyecto

El proyecto se organizará en los siguientes paquetes y clases:
Paquete: experimento
Clase: Experimento: Representa un experimento, con atributos como el nombre del experimento, la fecha de inicio y fin, y una lista de poblaciones de bacterias.
Clase: Población: Representa una población de bacterias, con atributos como el nombre de la población, la fecha de inicio y fin, el número de bacterias iniciales, la temperatura, las condiciones de luminosidad y la dosis de comida.
Paquete: gestión
Clase: GestiónExperimentos: Maneja la gestión de experimentos, incluyendo la creación, eliminación y visualización de experimentos.
Clase: GestiónPoblaciones: Maneja la gestión de poblaciones de bacterias, incluyendo la creación, eliminación y visualización de poblaciones.
Paquete: logica
Clase: LogicaExperimento: Implementa la lógica del experimento, incluyendo la gestión de la dosis de comida y la visualización de la información detallada de una población de bacterias.
Paquete: interfaz
Clase: Interfaz: Implementa la interfaz de usuario, incluyendo la creación de ventanas y componentes para interactuar con el usuario.
Paquete: utilidades
Clase: Utilidades: Contiene métodos útiles para la gestión de datos y la manipulación de strings.
Funcionalidades
Crear un nuevo experimento
Crear una población de bacterias y añadirla al experimento actual
Visualizar los nombres de todas las poblaciones de bacterias del experimento actual
Borrar una población de bacterias del experimento actual
Ver información detallada de una población de bacterias del experimento actual
Guardar y cargar experimentos en archivos de texto
