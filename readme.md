# Proyecto de Gestión de Citas Médicas

## Descripción

Este proyecto es una aplicación de gestión de citas médicas para un hospital. La aplicación permite a los doctores del área de Medicina Interna conocer cuántas citas tienen y a qué hora durante el día. La aplicación registra la siguiente información:

- **Doctores** (3 a 5 para el ejercicio, se pueden insertar manualmente)
  - Nombre
  - Apellido Paterno
  - Apellido Materno
  - Especialidad
- **Consultorios** (3 a 5 para el ejercicio, se pueden insertar manualmente)
  - Número de consultorio
  - Piso
- **Citas** (se generan dinámicamente)
  - Consultorio
  - Doctor
  - Horario de consulta
  - Nombre del paciente

## Requisitos

- Java 11 o superior
- Spring Boot 2.5.4 o superior
- Maven 3.6.3 o superior

## Instalación

1. Clona el repositorio:
   ```sh
   git clone https://github.com/elias230799/prueba-kosmos.git

La aplicación estará disponible en http://localhost:8080.

Documentación de la API
La documentación de la API está disponible en Swagger. Puedes acceder a ella en:
http://localhost:8080/swagger-ui.html

Estructura del Proyecto
Entidad Persona: Clase base para representar una persona.
Entidad Doctor: Extiende de Persona y añade la especialidad del doctor.
Entidad Consultorio: Representa un consultorio con número y piso.
Entidad Cita: Representa una cita médica con consultorio, doctor, horario y nombre del paciente.

Versión
Versión actual: 1.0.0-SNAPSHOT

Autor
Elias Camacho Ramirez

Licencia
Este proyecto está licenciado bajo la Licencia MIT - ver el archivo LICENSE para más detalles.


Configuración de la Base de Datos
Para modificar la configuración de la base de datos, edita el archivo `application.yml` en el directorio `src/main/resources`. Aquí puedes cambiar la URL, el nombre de usuario y la contraseña de la base de datos, así como otras configuraciones relacionadas con JPA y Hibernate.

Scripts de Base de Datos
Los scripts para la base de datos se encuentran en el directorio `src/main/resources/scripts`. Aquí puedes encontrar los archivos SQL necesarios para la creación y actualización de las tablas y datos de la base de datos.