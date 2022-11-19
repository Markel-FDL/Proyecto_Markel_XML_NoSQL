package org.example;

import java.io.*;

public class Creacion_fichero_Fotografo {
    public void creacion_fichero_fotografia() throws IOException, ClassNotFoundException {
        // ObjectOutputStream para poder escribir en el fichero binario de fotografos "Fotografos.dat". Si no existe, se crea automaticamente.
        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Fotografos.dat"));
        // Array con los nombres de los fotografos
        String[] nombres = {"Jordan Cronenweth", "Rodrigo Prieto", "Andrew Lesnie", "Gökhan Tiryaki", "Ernest Haller", "Yoshio Miyajima", "Caleb Deschanel", "Otello Martelli", "Winton Hoch", "Jack Cardiff", "Haskell Wexler", "Ron Fricke", "Boris Kaufman", "John Seale", "Hoyte van Hoytema", "Hong Kyung-pyo"};
        // Array con la edad de los fotografos
        String[] edad = {"1935-1996", "56", "1956-2015", "50", "1896-1970", "1909-1998", "78", "1902-2000", "1905-1979", "1914-2009", "1922-2015", "69", "1906-1980", "80", "51", "60"};
        // Insercion de los datos como clase Fotografo al fichero binario "Fotografos.dat"
        int i = 0;
        while (i < nombres.length){
            // Insertamos los fotografos
            escribir.writeObject(new Fotografo(i+1, nombres[i], edad[i]));
            i++;
        }
        escribir.writeObject(null);
        escribir.close();

        // ObjectInputStream para mostrar por pantalla los fotografos
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Fotografos.dat"));
        Fotografo fotografo = (Fotografo) mostrar.readObject();
        // Mostramos por pantalla todos los fotografos
        try {
            while (fotografo != null){
                fotografo.Mostrar();
                fotografo = (Fotografo) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
    }
}
