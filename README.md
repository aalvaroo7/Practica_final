# Proyecto de Gestión de Experimentos Bacteriológicos

Este proyecto es una aplicación de escritorio desarrollada en Java, diseñada para ayudar a los biólogos a gestionar cultivos de bacterias. La aplicación permite crear, eliminar y visualizar experimentos y poblaciones de bacterias, así como calcular la dosis de comida para cada día del experimento.

## Estructura del Proyecto

El proyecto se organiza en los siguientes paquetes y clases:

- **Paquete `Gestion_experimentos`**: Contiene las clases `Experimento` y `Bacteria` que representan un experimento y una bacteria respectivamente.

- **Paquete `Gestion_poblaciones_bacterias`**: Contiene la clase `PoblacionBacterias` que representa una población de bacterias.

- **Paquete `gestion`**: Contiene las clases `GestionExperimentos` y `GestionPoblaciones` que manejan la gestión de experimentos y poblaciones de bacterias respectivamente.

- **Paquete `logica`**: Contiene la clase `LogicaExperimento` que implementa la lógica del experimento.

- **Paquete `Utilidades`**: Contiene la clase `Utilidades` que proporciona métodos útiles para la gestión de datos y la manipulación de strings.

- **Paquete `GUI`**: Contiene la clase `InterfazUsuario` que implementa la interfaz de usuario.

## Funcionalidades

- Crear un nuevo experimento.
- Crear una población de bacterias y añadirla al experimento actual.
- Visualizar los nombres de todas las poblaciones de bacterias del experimento actual.
- Borrar una población de bacterias del experimento actual.
- Ver información detallada de una población de bacterias del experimento actual.
- Guardar y cargar experimentos en archivos de texto.
- 
## Cómo ejecutar

El proyecto se puede ejecutar a través de un archivo JAR, lo que permite ejecutar la aplicación de escritorio en cualquier sistema operativo que tenga instalado Java. Para ejecutar el archivo JAR, se debe abrir una terminal y ejecutar el siguiente comando:

```bash
java -jar Practica_final.jar

