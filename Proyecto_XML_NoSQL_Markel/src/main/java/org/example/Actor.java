package org.example;

import java.io.Serializable;

public class Actor implements Serializable {

    int id_actor;
    String nombre;
    String edad;
    //int participaciones;

    public Actor() {

    }

    public Actor(int id_actor, String nombre, String edad) {
        this.id_actor = id_actor;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId_actor() {
        return id_actor;
    }

    public void setId_actor(int id_actor) {
        this.id_actor = id_actor;
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
        System.out.println("ID: " + id_actor +
                " Nombre: " + nombre +
                " Edad: " + edad);
    }
}

