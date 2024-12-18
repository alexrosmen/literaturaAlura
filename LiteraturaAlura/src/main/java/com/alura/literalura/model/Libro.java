package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name= "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "idioma")
    private String idioma;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Column(name = "numero_descargas")
    private Double numero_Descargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro, Autor autor) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma().isEmpty() ? "Desconocido" : (String) datosLibro.idioma().get(0).idioma();  // Ajuste aquí
        this.numero_Descargas = Double.valueOf(datosLibro.numero_Descargas());
        this.autor = autor;
    }

    public Libro(String titulo, String idioma, Autor autor, Double aDouble) {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*********** Libro ***********\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autor: ").append(autor != null ? autor.getNombre() : "Desconocido").append("\n");
        sb.append("Idioma: ").append(idioma).append("\n");
        sb.append("Número de descargas: ").append(numero_Descargas).append("\n");
        sb.append("****************************\n");
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumero_Descargas() {
        return numero_Descargas;
    }

    public void setNumero_Descargas(Double numero_Descargas) {
        this.numero_Descargas = numero_Descargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }



    public Double numero_Descargas() {
        return numero_Descargas;
    }
}







