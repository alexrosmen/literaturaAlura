# LiterAlura
LiterAlura es una aplicación de gestión de libros que permite buscar, registrar y listar libros y autores utilizando datos de la API de Gutendex. 
La aplicación ofrece funcionalidades como búsqueda de libros por título, listado de libros y autores registrados, autores vivos en un año determinado, libros por idioma, top 10 de libros más descargados y estadísticas de descargas.

## Características

- **Búsqueda de libros por título**: Busca libros por su título utilizando la API de Gutendex.
- **Registro de libros y autores**: Almacena libros y autores en una base de datos.
- **Listado de libros y autores registrados**: Muestra los libros y autores almacenados en la base de datos.
- **Autores vivos en un año determinado**: Muestra los autores que estaban vivos en un año específico.
- **Listado de libros por idioma**: Filtra y muestra los libros según su idioma.
- **Top 10 libros más descargados**: Muestra los 10 libros más descargados.
- **Estadísticas de descargas**: Muestra estadísticas como el número máximo, mínimo y promedio de descargas.

## Requisitos

- Java 17 o superior
- Spring Boot 2.5.0 o superior
- Postgresql o cualquier otra base de datos compatible con JPA/Hibernate
- Maven 3.6.0 o superior
  
## Uso

### Menú Principal

La aplicación presenta un menú interactivo con las siguientes opciones:
![Screenshot_164](https://github.com/AleVaz70/LiterAlura/assets/124855251/d193bbad-6c30-416d-813c-b4840f232f39)

### Ejemplo de uso

- **Buscar libro por título**: Ingresa el título del libro y la aplicación buscará en la API de Gutendex, registrará el libro y su autor en la base de datos si no están ya registrados.


![Screenshot_165](https://github.com/AleVaz70/LiterAlura/assets/124855251/e84ce48e-a63e-46fd-9420-a35b796e60be)
![Screenshot_166](https://github.com/AleVaz70/LiterAlura/assets/124855251/bfd3eb12-5d82-43e6-8d0d-5466f43a1664)

**Lista de libros registrados**: Muestra todos los libros almacenados en la base de datos.(Ejemplo)
![Screenshot_167](https://github.com/AleVaz70/LiterAlura/assets/124855251/6ff67484-c8fc-4511-9b8d-085fcb277dbe)

**Lista de autores registrados**: Muestra todos los autores almacenados en la base de datos.(Ejemplo)
![Screenshot_168](https://github.com/AleVaz70/LiterAlura/assets/124855251/1cd92866-9dfb-4035-a160-eae3df6d6009)

**Lista de autores vivos**: Muestra todos los autores vivos en un año determinado almacenados en la base de datos, si no existen muestra un mensaje.(Ejemplo)

![Screenshot_169](https://github.com/AleVaz70/LiterAlura/assets/124855251/50a8c8bf-0561-43e2-8fba-da4fcc0d7467)
![Screenshot_170](https://github.com/AleVaz70/LiterAlura/assets/124855251/4ccdcbd8-83b5-46bd-8b7e-499276f91166)

**Lista de libros por idioma**: Muestra todos los libros almacenados por idioma en la base de datos.(Ejemplo)
![Screenshot_179](https://github.com/AleVaz70/LiterAlura/assets/124855251/500163ea-81c9-48ab-93a6-71889d5c77cc)

**Top 10 libros más descargados**: Muestra los 10 libros más descargados almacenados en la base de datos.
![Screenshot_176](https://github.com/AleVaz70/LiterAlura/assets/124855251/296c6299-3977-4e61-80c9-e22d489d9fdf)

**Estadísticas**: Estadísticas basadas en los libros almacenados en la base de datos.
![Screenshot_178](https://github.com/AleVaz70/LiterAlura/assets/124855251/b1e562ad-060c-40c7-bb61-b2fad1012b16)
