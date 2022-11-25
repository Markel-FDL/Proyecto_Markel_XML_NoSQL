package org.example;

import org.xmldb.api.base.XMLDBException;

import java.io.*;


public class Creacion_fichero_Actor {
    Todo_Funciones_y_Creacion_Fichero_Pelicula funciones = new Todo_Funciones_y_Creacion_Fichero_Pelicula();

    public void creacion_fichero_actor() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        // ObjectOutputStream para poder escribir en el fichero binario de actores "Actores.dat". Si no existe, se crea automaticamente.
        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Actores.dat"));
        // Array con los nombres de los actores
        String[] nombres = {"Cary Grant", "Alain Delon", "Alberto Sordi", "Alec Guinness", "Alfredo Landa", "Al Pacino", "Anthony Hopkins", "Bruno Ganz", "Burt Lancaster", "Buster Keaton", "Chang Chen", "Charles Chaplin", "Charles Laughton", "Choi Min-sik", "Christian Bale", "Christopher Lee"};
        // Array con la edad de los actores
        String[] edad = {"1904-1986", "86", "1920-2003", "1914-2000", "1933-2013", "82", "84", "1941-2019", "1913-1994", "1895-1966", "46", "1889-1977", "1899-1962", "60", "48", "1922-2015"};
        // Insercion de los datos como clase Actor al fichero binario "Actores.dat"
        int i = 0;
        while (i < nombres.length) {
            // Insertamos los actores
            escribir.writeObject(new Actor(i + 1, nombres[i], edad[i]));
            i++;
        }
        escribir.writeObject(null);
        escribir.close();

        // ObjectInputStream para mostrar por pantalla los actores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Actores.dat"));
        Actor actor = (Actor) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (actor != null) {
                actor.Mostrar();
                actor = (Actor) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        funciones.Actores_XML();
    }
}
