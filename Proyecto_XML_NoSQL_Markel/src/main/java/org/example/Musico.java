package org.example;

import java.io.Serializable;

public class Musico implements Serializable {

    int id_musico;
    String nombre;
    String edad;
    // int participaciones;

    public Musico() {

    }

    public Musico(int id_musico, String nombre, String edad) {
        this.id_musico = id_musico;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId_musico() {
        return id_musico;
    }

    public void setId_musico(int id_musico) {
        this.id_musico = id_musico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void Mostrar() {
        System.out.println("ID: " + id_musico +
                " Nombre: " + nombre +
                " Edad: " + edad);
    }
}
