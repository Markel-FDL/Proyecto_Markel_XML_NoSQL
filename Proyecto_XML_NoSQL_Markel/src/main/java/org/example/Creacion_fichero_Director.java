package org.example;

import java.io.*;

public class Creacion_fichero_Director {
    public void creacion_fichero_director() throws IOException, ClassNotFoundException {
        // ObjectOutputStream para poder escribir en el fichero binario de directores "Directores.dat". Si no existe, se crea automaticamente.
        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Directores.dat"));
        // Array con los nombres de los directores
        String[] nombres = {"Christopher Nolan", "Steven Spielberg", "Martin Scorsese", "Stanley Kubrick", "Alfred Hitchcock", "Hayao Miyazaki", "Charles Chaplin", "Akira Kurosawa", "Billy Wilder", "Quentin Tarantino", "Makoto Shinkai", "Roman Polanski", "James Cameron", "Ridley Scott", "Pedro Almodóvar", "Alfonso Cuarón"};
        // Array con la edad de los directores
        String[] edad = {"52" , "75", "79", "1928-1999", "1899-1980", "81", "1889-1977", "1910-1998", "1906-2002", "59", "49", "89", "68", "84", "73", "60"};
        // Insercion de los datos como clase Director al fichero binario "Directores.dat"
        int i = 0;
        while (i < nombres.length){
            // Insertamos los directores
            escribir.writeObject(new Director(i+1, nombres[i], edad[i]));
            i++;
        }
        escribir.writeObject(null);
        escribir.close();

        // ObjectInputStream para mostrar por pantalla los directores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Directores.dat"));
        Director director = (Director) mostrar.readObject();
        // Mostramos por pantalla todos los directores
        try {
            while (director != null){
                director.Mostrar();
                director = (Director) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
    }
}
