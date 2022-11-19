package org.example;

import java.io.Serializable;

public class Director implements Serializable {

    int id_director;
    String nombre;
    String edad;
    //int participaciones;

    public Director(){

    }

    public Director(int id_director, String nombre, String edad){
        this.id_director = id_director;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId_director() {
        return id_director;
    }

    public void setId_director(int id_director) {
        this.id_director = id_director;
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

    public void Mostrar(){
        System.out.println("ID: " + id_director +
                " Nombre: " + nombre +
                " Edad: " + edad);
    }

  /*  public Director Mostrar2(){
        Director ss = "ID: " + id_director +
                " Nombre: " + nombre +
                " Edad: " + edad;
        return
    }*/
}
