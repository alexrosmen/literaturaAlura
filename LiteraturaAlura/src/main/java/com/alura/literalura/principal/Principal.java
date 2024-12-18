package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



@Service
public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

   @Autowired
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    ***** ELIJA UNA OPCION *****
                    1- Buscar libro por título
                    2- Lista de libros registrados
                    3- Lista de autores registrados
                    4- Lista de autores vivos en un año determinado
                    5- Lista de libros por idioma
                    6- Top 10 libros más descargados
                    7- Estadísticas
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos();
                case 5 -> manejarMenuIdiomas();
                case 6 -> top10();
                case 7 -> estadisticas();
                case 0 -> System.out.println("Cerrando la aplicación. Gracias por sus consultas.");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el título del libro: ");
        String nombreLibro = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        if (datos.resultados() != null && !datos.resultados().isEmpty()) {
            return datos.resultados().get(0); // Tomar el primer resultado
        }
        return null;
    }
    private Libro almacenarLibro(DatosLibro datosLibro, Autor autor) {
        List<Libro> existingLibros = libroRepository.findByTitulo(datosLibro.titulo());
        if (!existingLibros.isEmpty()) {
            return existingLibros.get(0); // Devuelve el primer libro encontrado
        }
        Libro libro = new Libro(datosLibro, autor);
        return libroRepository.save(libro);
    }

     private void buscarLibroPorTitulo() {
        DatosLibro datos = getDatosLibro();
        if (datos != null) {
            Autor autor = datos.autor().stream()
                    .map(da -> {
                        Autor existingAutor = autorRepository.findByNombre(da.nombre());
                        if (existingAutor != null) {
                            return existingAutor;
                        } else {
                            Autor nuevoAutor = new Autor(da);
                            return autorRepository.save(nuevoAutor);
                        }
                    })
                    .findFirst()
                    .orElse(null);
            if (autor != null) {
                Libro libro = almacenarLibro(datos, autor);
               System.out.println(/*"Libro almacenado:  "+ */libro);
            }
        } else {
            System.out.println("********************************************************");
            System.out.println("* No se encontraron datos para el título proporcionado *");
            System.out.println("********************************************************");
        }
    }



    private void listarLibrosRegistrados() {
        System.out.println("Los libros registrados son: ");
        List<Libro> libros = libroRepository.findAll();
        if (!libros.isEmpty()) {
            libros.forEach(System.out::println);
        } else {
            System.out.println("******************************");
            System.out.println("* No hay libros registrados. *");
            System.out.println("******************************");
        }
    }


    private void listarAutoresRegistrados() {
        System.out.println("Los autores registrados son: ");
        List<Autor> autores = autorRepository.findAll();
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("No hay autores registrados.");
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Escribe el año que quieras saber qué autor estaba vivo: ");
        int fecha = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = autorRepository.findByFechaNacimientoLessThanEqualAndFechaFallecimientoGreaterThanEqual(fecha, fecha);
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
            System.out.println("° NO SE ENCONTRARON AUTORES VIVOS PARA EL AÑO PROPORCIONADO.°");
            System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
        }
    }
    private void mostrarMenuIdiomas() {
        System.out.println("***** ELIJA UN IDIOMA *****");
        System.out.println("1. Español ");
        System.out.println("2. Francés ");
        System.out.println("3. Inglés ");
        System.out.println("4. Portugués ");
        System.out.println("0. Volver al menú principal");
        System.out.println("***************************");
        System.out.println("Elija una opción: ");
    }

    public void manejarMenuIdiomas() {
        int opcionIdioma = -1;
        while (opcionIdioma != 0) {
            mostrarMenuIdiomas();
            opcionIdioma = teclado.nextInt();
            teclado.nextLine(); // Limpiar el buffer del scanner

            switch (opcionIdioma) {
                case 1:
                    listarLibrosPorIdioma("es");
                    break;
                case 2:
                    listarLibrosPorIdioma("fr");
                    break;
                case 3:
                    listarLibrosPorIdioma("en");
                    break;
                case 4:
                    listarLibrosPorIdioma("pt");
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        }
    }
    private String obtenerNombreIdioma(String siglaIdioma) {
        switch (siglaIdioma) {
            case "es":
                return "Español";
            case "fr":
                return "Francés";
            case "en":
                return "Inglés";
            case "pt":
                return "Portugués";
            default:
                return "Desconocido";
        }
    }

    private void listarLibrosPorIdioma(String idioma) {
        String nombreIdioma = obtenerNombreIdioma(idioma);
        List<Libro> libros = libroRepository.buscarLibrosPorIdioma(idioma);
        if (!libros.isEmpty()) {
            System.out.println("Los libros en " + nombreIdioma + " son: ");
            libros.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron libros en el idioma " + nombreIdioma + ".");
        }
    }


    public void top10() {
        // Top 10 libros más descargados
        System.out.println("Top 10 libros más descargados");
        libroRepository.findAll().stream()
                .sorted(Comparator.comparing(Libro::getNumero_Descargas).reversed())
                .limit(10)
                .forEach(libro -> System.out.println(libro.getTitulo().toUpperCase()));
    }


    public void estadisticas() {
    //Trabajando con estadisticas
    DoubleSummaryStatistics est = libroRepository.findAll().stream()
            .filter(d -> d.numero_Descargas() > 0)
            .collect(Collectors.summarizingDouble(Libro::getNumero_Descargas));
    System.out.println("Cantidad media de descargas: " + Math.round(est.getAverage()));//est.getAverage());
    System.out.println("Cantidad máxima de descargas: " + Math.round(est.getMax()));
    System.out.println("Cantidad mínima de descargas: " + Math.round(est.getMin()));
    System.out.println("Cantidad de registros evaluados para calcular las estadisticas: " + est.getCount());
}
}

