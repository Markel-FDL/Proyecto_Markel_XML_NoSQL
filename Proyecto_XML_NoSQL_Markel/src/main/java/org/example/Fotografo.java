package org.example;

import java.io.Serializable;

public class Fotografo implements Serializable {

    int id_fotografo;
    String nombre;
    String edad;
    //int participaciones;

    public Fotografo() {

    }

    public Fotografo(int id_fotografo, String nombre, String edad) {
        this.id_fotografo = id_fotografo;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId_fotografo() {
        return id_fotografo;
    }

    public void setId_fotografo(int id_fotografo) {
        this.id_fotografo = id_fotografo;
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
        System.out.println("ID: " + id_fotografo +
                " Nombre: " + nombre +
                " Edad: " + edad);
    }
}
