# Administración de Empleados - Spring Boot 3 API

📋 Descripción
API RESTful para gestión de empleados desarrollada con Spring Boot 3.x. Incluye operaciones CRUD completas, transferencias de salario entre empleados y análisis estadístico de salarios.

# 🚀 Características Principales
# ✅ Operaciones CRUD Completas
    - Crear nuevos empleados
    - Leer todos los empleados o específicos
    - Actualizar información de empleados
    - Eliminar empleados con validaciones

# ✅ Funcionalidades Avanzadas
    - Transferencia de salario entre empleados
    - Cálculo de promedio salarial automático
    - Filtrado de empleados con salario superior al promedio
    - Validación robusta de datos de entrada

# ✅ Tecnologías Implementadas
    - Spring Boot 3.x con arquitectura en capas
    - Spring Data JPA para persistencia
    - Lombok para reducción de código boilerplate
    - Validación Bean Validation
    - Manejo centralizado de excepciones
    - Logging con SLF4J

# 🏗️ Arquitectura del Proyecto

- src/main/java/com.adminempleado/
- ├── 📁 controller/
- │   └── EmployeeController.java      # Controlador REST
- ├── 📁 service/
- │   ├── IEmployeeService.java        # Interfaz del servicio
- │   └── EmployeeServiceImpl.java     # Implementación del servicio
- ├── 📁 repository/
- │   └── EmployeeDAO.java             # Repositorio JPA
- ├── 📁 entity/
- │   └── Employee.java                # Entidad JPA
- └── 📁 dto/
-     └── EmployeeDTO.java             # Objeto de Transferencia  

# 🔧 Requisitos del Sistema
    - Java 17 o superior
    - Maven 3.6+
    - Spring Boot 3.x
    - Base de datos (Oracle 19c)
    - Postman o similar para pruebas
# 🛠️ Instalación y Configuración
1. Clonar el repositorio
   - git clone https://github.com/AlexRubenPaguay/AdminEmpleadoAPI.git
   - cd AdminEmpleadoAPI

2. Configurar la base de datos
  - spring.datasource.url=jdbc:oracle:thin:@//localhost:1522/xepdb1
  - spring.datasource.username=APEX_UNO
  - spring.datasource.password=apex_uno
  - spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

3. Construir y ejecutar
   # Construir el proyecto
    -  mvn clean install

  # Ejecutar la aplicación
     - mvn spring-boot:run

  # O ejecutar el JAR
    - java -jar target/api-admin-empleado-0.0.1-SNAPSHOT.jar

# 📚 Endpoints de la API
- Base URL: http://localhost:7581/v1/Employee/
- Método	-->Endpoint	        -->Descripción	
- GET	    -->/allEmployees	    -->Obtener todos los empleados	
- POST	  -->/create	          -->Crear nuevo empleado	
- PUT	    -->/update/{id}	    -->Actualizar empleado por ID	
- DELETE	-->/delete/{id}	    -->Eliminar empleado por ID	
- POST	  -->/transfer/{depositId}/{receiveId}/{quantity}	-->Transferir salario entre empleados	
- GET	    -->/averageSalary	                              -->Empleados con salario superior al promedio	

# 🔍 Ejemplos de Uso
1. Obtener todos los empleados
   <img width="1365" height="796" alt="imagen" src="https://github.com/user-attachments/assets/02eb1cd2-739f-4711-87ab-d6d41e15c587" />

2.- Obtener empleados con salario superior al promedio
<img width="1380" height="672" alt="imagen" src="https://github.com/user-attachments/assets/bdc573cd-b464-43a6-b8ed-d5ec56ad7437" />

¡Gracias por utilizar **Administración de Empleados - Spring Boot 3 API !** Si tienes alguna duda o problema, no dudes en abrir un issue en GitHub.

Este archivo README cubre desde la instalación hasta la ejecución, detalles sobre las tecnologías utilizadas, la configuración de la base de datos Oracle, 
y cómo acceder a cada end-point. ¡Espero que te sea útil para tu proyecto!  
