package org.example;

import java.io.Serializable;
import java.security.PublicKey;

public class Pelicula implements Serializable {

    int id_pelicula;
    String nombre_pelicula;
    Director director;
    Musico musico;
    Fotografo fotografo;
    int ano;
    int duracion;
    Actor actor_prota;
    Actor actor_secundario;
    Double puntuacion;

    int cantidad_puntuado;

    public Pelicula() {

    }

    public Pelicula(int id_pelicula, String nombre_pelicula, Director director, Musico musico, Fotografo fotografo, int ano, int duracion, Actor actor_prota, Actor actor_secundario, double puntuacion) {
        this.id_pelicula = id_pelicula;
        this.nombre_pelicula = nombre_pelicula;
        this.director = director;
        this.musico = musico;
        this.fotografo = fotografo;
        this.ano = ano;
        this.duracion = duracion;
        this.actor_prota = actor_prota;
        this.actor_secundario = actor_secundario;
        this.puntuacion = puntuacion;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getNombre_pelicula() {
        return nombre_pelicula;
    }

    public void setNombre_pelicula(String nombre_pelicula) {
        this.nombre_pelicula = nombre_pelicula;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Musico getMusico() {
        return musico;
    }

    public void setMusico(Musico musico) {
        this.musico = musico;
    }

    public Fotografo getFotografo() {
        return fotografo;
    }

    public void setFotografo(Fotografo fotografo) {
        this.fotografo = fotografo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Actor getActor_prota() {
        return actor_prota;
    }

    public void setActor_prota(Actor actor_prota) {
        this.actor_prota = actor_prota;
    }

    public Actor getActor_secundario() {
        return actor_secundario;
    }

    public void setActor_secundario(Actor actor_secundario) {
        this.actor_secundario = actor_secundario;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion += puntuacion;
    }

    public int getCantidad_puntuado() {
        return cantidad_puntuado;
    }

    public void setCantidad_puntuado(int cantidad_puntuado) {
        this.cantidad_puntuado += cantidad_puntuado;
    }

    public void Mostrar() {
        System.out.println("ID: " + id_pelicula +
                " Nombre: " + nombre_pelicula +
                " Director: " + director.nombre +
                " Músico: " + musico.nombre +
                " Fotógrafo: " + fotografo.nombre +
                " Año: " + ano +
                " Duración: " + duracion +
                " Actor Principal: " + actor_prota.nombre +
                " Actor Secundario: " + actor_secundario.nombre +
                "/nPuntuación media: " + puntuacion / cantidad_puntuado);
    }
}
