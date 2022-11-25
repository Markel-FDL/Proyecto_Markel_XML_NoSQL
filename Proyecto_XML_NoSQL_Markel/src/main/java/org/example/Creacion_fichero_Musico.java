package org.example;

import org.xmldb.api.base.XMLDBException;

import java.io.*;

public class Creacion_fichero_Musico {
    Todo_Funciones_y_Creacion_Fichero_Pelicula funciones = new Todo_Funciones_y_Creacion_Fichero_Pelicula();

    public void creacion_fichero_musico() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        // ObjectOutputStream para poder escribir en el fichero binario de compositores "Compositores.dat". Si no existe, se crea automaticamente.
        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Compositores.dat"));
        // Array con los nombres de los compositores
        String[] nombres = {"Max Steiner", "Ennio Morricone", "Nino Rota", "John Williams", "Bernard Herrmann", "Vangelis", "Hans Zimmer", "John Barry", "Nicola Piovani", "Henry Mancini", "James Horner", "Thomas Newman", "Bill Conti", "Maurice Jarre", "Danny Elfman", "Wojciech Kilar"};
        // Array con la edad de los compositores
        String[] edad = {"1888-1971", "1928-2020", "1911-1979", "90", "1911-1975", "1943-2022", "65", "1933-2011", "76", "1924-1994", "1953-2015", "66", "80", "1924-2009", "69", "1932-2013"};
        // Insercion de los datos como clase Musico al fichero binario "Compositores.dat"
        int i = 0;
        while (i < nombres.length) {
            // Insertamos los directores
            escribir.writeObject(new Musico(i + 1, nombres[i], edad[i]));
            i++;
        }
        escribir.writeObject(null);
        escribir.close();

        // ObjectInputStream para mostrar por pantalla los compositores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Compositores.dat"));
        Musico musico = (Musico) mostrar.readObject();
        // Mostramos por pantalla todos los compositores
        try {
            while (musico != null) {
                musico.Mostrar();
                musico = (Musico) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        funciones.Musicos_XML();
    }
}
