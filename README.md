
# 🩺 API de Reservas de Citas Médicas — *DoctorYa*

[![Build](https://github.com/alejoved/DoctorYa-Java-SpringBoot/actions/workflows/ci.yml/badge.svg)](https://github.com/alejoved/DoctorYa-Java-SpringBoot/actions)
[![License](https://img.shields.io/github/license/alejoved/DoctorYa-Java-SpringBoot)](LICENSE)
[![Last Commit](https://img.shields.io/github/last-commit/alejoved/DoctorYa-Java-SpringBoot)](https://github.com/alejoved/DoctorYa-Java-SpringBoot/commits)
[![Java](https://img.shields.io/badge/java-17-blue.svg)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-brightgreen)](https://spring.io/projects/spring-boot)
[![Dockerized](https://img.shields.io/badge/docker-ready-blue)](#docker)

---

Sistema backend para la gestión de citas médicas entre pacientes y doctores. Este proyecto ha sido desarrollado como una muestra de habilidades de un **desarrollador backend senior**, incorporando buenas prácticas de arquitectura, reglas de negocio robustas, pruebas automatizadas y despliegue en contenedores.

---

## 🚀 Funcionalidades Principales

### 👨‍⚕️ Doctores
- CRUD completo (`id`, `name`, `specialty`)
  
### 🧑 Pacientes
- CRUD completo (`id`, `name`, `email`)
  
### 📆 Citas Médicas
- Crear, listar y cancelar citas
- Atributos: `id`, `doctorId`, `patientId`, `startTime`, `endTime`, `notes`

---

## 📋 Reglas de Negocio

- ❌ No se permite el **solapamiento de citas** para un mismo doctor
- ❌ Un paciente no puede tener **dos citas al mismo tiempo**
- ✅ `startTime` debe ser **anterior a** `endTime`
- ✅ Posibilidad de consultar la **disponibilidad de un doctor**
- ⚠️ Validaciones personalizadas con manejo de excepciones controlado

---

## ⚙️ Tecnologías Utilizadas

| Categoría         | Tecnología                        |
|------------------|-----------------------------------|
| Lenguaje          | Java                              |
| Framework         | Spring Boot                       |
| ORM               | JPA + JPQL                        |
| Base de datos     | PostgreSQL                        |
| Documentación API | Swagger                           |
| Testing           | JUnit                             |
| Contenedores      | Docker, Podman, Minikube (K8s)    |


## 🗂️ Arquitectura del Proyecto

> 🧱 Basada en principios de **Arquitectura Hexagonal** / Clean Architecture

```
src/
├── application/        # Casos de uso y servicios de aplicación
├── domain/             # Modelos, interfaces y reglas de negocio
├── infrastructure/     # Adaptadores secundarios (DB, servicios externos)
├── interface/          # Adaptadores primarios (controllers REST)
├── shared/             # Utilidades, constantes, middlewares
└── main.ts             # Punto de entrada principal
```

✅ Separación clara de responsabilidades  
✅ Diseño orientado a dominios  
✅ Módulos desacoplados y escalables

---

## 🚀 Primeros pasos para estudiantes

### 1️⃣ Haz fork del repositorio
Haz clic en el botón **Fork** en la parte superior derecha de este repositorio.

### 2️⃣ Clona tu fork
```bash
git clone https://github.com/tu-usuario/doctorya
cd doctorya
```

### 3️⃣ Configura las variables de entorno
Copia el archivo `.env.example` y renómbralo a `.env`:
```bash
cp .env.example .env
```
Luego abre el archivo `.env` y llena tus propias credenciales de PostgreSQL:
```
HOST_DB=127.0.0.1
PORT_DB=5432
POSTGRES_DB=doctorya
USERNAME_DB=postgres
PASSWORD_DB=tu_contraseña
```
### 4️⃣ Corre los tests
```bash
./mvnw test
```
---

## 🧪 Ejecución y Pruebas

### ▶️ Ejecución Local
```bash
# Instalar dependencias
mvn install

# Ejecutar el proyecto
java -jar target/doctorya-app.jar
```

### 🧪 Ejecutar Tests
```bash
mvn test
```
## Ejecutar pruebas desde terminal

./mvnw test

Si aparece:
zsh: permission denied

Ejecutar:
chmod +x mvnw

### 📘 Documentación Swagger
Disponible automáticamente en:  
`http://localhost:8080/swagger-ui.html`

---

## 📦 Docker / Contenedores

### 🐳 Build & Run con Podman
```bash
podman build -t doctorya-app:latest .
podman compose up
```

---

## ☸️ Despliegue en Kubernetes con Minikube

### ✅ Requisitos
- [x] Minikube instalado
- [x] Podman (o Docker)
- [x] Manifiestos en `k8s/`

### 🚀 Pasos de Despliegue
```bash
minikube delete
minikube start
minikube addons enable metrics-server

# Crear y exportar imagen
podman build -t doctorya-app:latest .
podman save -o doctorya-app.tar doctorya-app:latest

# Cargar imagen en Minikube
minikube image load doctorya-app.tar

# Aplicar manifiestos K8s
kubectl apply -f k8s/

# Ver logs o exponer servicio
kubectl logs <pod-name>
minikube service doctorya-service
```

---

## 🧠 Buenas Prácticas Aplicadas

1. **Separación modular clara** (`Appointment`, `Auth`, `Patient`, `Physician`)
2. **Subcarpetas por responsabilidad**: `Controller`, `DTO`, `Entity`, `Service`, `Repository`
3. **Centralización de lógica común**: `utils/`, `exceptions/`
4. **Validaciones robustas**: Uso de excepciones personalizadas
5. **Documentación y demo accesible**: Swagger + comandos en README
6. **Preparado para producción**: Docker, K8s, configuración desacoplada

---

## 📌 Qué Demuestra Este Proyecto

| Habilidad                           | Evidencia                                                 |
|------------------------------------|------------------------------------------------------------|
| Diseño de dominios                 | Entidades ricas + reglas de negocio aplicadas             |
| Arquitectura escalable             | Hexagonal, modular, separación de capas                   |
| Testing profesional                | JUnit, validaciones y casos límite cubiertos              |
| Seguridad                          | JWT, control de roles (si aplica en tu repo)              |
| DevOps básico                      | Docker, Podman, Minikube                                  |
| Documentación y mantenimiento      | Código limpio + README claro + Swagger                    |

---

## 👤 Autor

Desarrollado por **Alejandro Aguirre**  
[LinkedIn](https://www.linkedin.com/in/jorge-alejandro-aguirre-gutierrez-1836a0187) • [GitHub](https://github.com/alejoved) • Backend Engineer

---

## 📄 Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).