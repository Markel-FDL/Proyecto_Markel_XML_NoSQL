package org.example;

import com.groupdocs.viewer.Viewer;
import com.groupdocs.viewer.options.PngViewOptions;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;

public class Todo_Funciones_y_Creacion_Fichero_Pelicula {

    static Scanner scanner = new Scanner(System.in);
    static List<Director> directores = new ArrayList<Director>();
    static List<Musico> musicos = new ArrayList<Musico>();
    static List<Fotografo> fotografos = new ArrayList<Fotografo>();
    static List<Actor> actores = new ArrayList<Actor>();
    static List<Pelicula> peliculas = new ArrayList<Pelicula>();

    public void Crear_fichero_Pelicula() throws IOException, ClassNotFoundException {

        // Recoger en un array a los directores

        ObjectInputStream recoger_director = new ObjectInputStream(new FileInputStream("Directores.dat"));

        Director director = (Director) recoger_director.readObject();

        // Mostramos por pantalla todos los actores
        try {
            while (director != null) {
                directores.add(director);
                director = (Director) recoger_director.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
        for (Director c : directores) {
            System.out.println("ID: " + c.id_director + " Nombre: " + c.nombre + " Edad: " + c.edad);
        }

        recoger_director.close();

        // Recoger en un array a los compositores

        ObjectInputStream recoger_musico = new ObjectInputStream(new FileInputStream("Compositores.dat"));

        Musico musico = (Musico) recoger_musico.readObject();

        // Mostramos por pantalla todos los actores
        try {
            while (musico != null) {
                musicos.add(musico);
                musico = (Musico) recoger_musico.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
        for (Musico c : musicos) {
            System.out.println("ID: " + c.id_musico + " Nombre: " + c.nombre + " Edad: " + c.edad);
        }

        recoger_musico.close();

        // Recoger en un array a los fotografos

        ObjectInputStream recoger_fotografo = new ObjectInputStream(new FileInputStream("Fotografos.dat"));

        Fotografo fotografo = (Fotografo) recoger_fotografo.readObject();

        // Mostramos por pantalla todos los actores
        try {
            while (fotografo != null) {
                fotografos.add(fotografo);
                fotografo = (Fotografo) recoger_fotografo.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
        for (Fotografo c : fotografos) {
            System.out.println("ID: " + c.id_fotografo + " Nombre: " + c.nombre + " Edad: " + c.edad);
        }

        recoger_fotografo.close();

        // Recoger en un array a los actores

        ObjectInputStream recoger_actores = new ObjectInputStream(new FileInputStream("Actores.dat"));

        Actor actor = (Actor) recoger_actores.readObject();

        // Mostramos por pantalla todos los actores
        try {
            while (actor != null) {
                actores.add(actor);
                actor = (Actor) recoger_actores.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
        for (Actor c : actores) {
            System.out.println("ID: " + c.id_actor + " Nombre: " + c.nombre + " Edad: " + c.edad);
        }

        recoger_actores.close();

        // ObjectOutputStream para poder escribir en el fichero binario de actores "Peliculas.dat". Si no existe, se crea automaticamente.
        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Peliculas.dat"));
        // Array con los nombres de los actores
        String[] nombres = {"Glory", "Al filo de la noticia", "El exorcista", "Ladrón que roba a ladrón", "La gran evasión", "Muerte entre las flores", "Acorralado (Rambo)", "Terminator", "Toro salvaje", "Tres colores", "Zombi", "Alien: El octavo pasajero", "Blade Runner", "Centauros del desierto", "Ciudadano Kane", "El puente sobre el río Kwai"};
        // Array con la edad de los actores
        int[] ano = {1990, 1988, 1975, 2007, 1963, 1990, 1982, 1984, 1981, 1993, 2018, 1979, 1982, 1961, 1946, 1958};
        int[] duracion = {120, 133, 132, 98, 172, 115, 93, 107, 129, 90, 98, 117, 117, 119, 119, 161};
        // Insercion de los datos como clase Actor al fichero binario "Actores.dat"
        Random random = new Random();
        int i = 0;
        while (i < nombres.length) {
            // Insertamos los actores
            escribir.writeObject(new Pelicula(i + 1, nombres[i], directores.get(i), musicos.get(i), fotografos.get(i), ano[i], duracion[i], actores.get(random.nextInt(1, 16)), actores.get(random.nextInt(1, 16)), 0.00));
            i++;
        }
        escribir.writeObject(null);
        escribir.close();

        // ObjectInputStream para insertar los actores en el array peliculas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (pelicula != null) {
                peliculas.add(pelicula);
                pelicula = (Pelicula) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        mostrar.close();

    }

    public void Array_Peliculas() throws IOException, ClassNotFoundException {
        // ObjectInputStream para insertar los actores en el array peliculas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (pelicula != null) {
                peliculas.add(pelicula);
                pelicula = (Pelicula) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        mostrar.close();
    }

    // DIRECTORES
    // Mostrar todos los directores

    public void Mostrar_Directores(String s) throws IOException, ClassNotFoundException {
        // ObjectInputStream para mostrar por pantalla los directores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Directores.dat"));
        Director director = (Director) mostrar.readObject();
        // Mostramos por pantalla todos los directores
        try {
            while (director != null) {
                director.Mostrar();
                director = (Director) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
        if (s == null) {
            Main.Directores();
        }
    }

    // Mostrar director por nombre

    public void Mostrar_Director_por_Nombre() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar al director por su nombre");

        Mostrar_Directores("1");

        try {
            System.out.println("Nombre del director: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla los directores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Directores.dat"));
        Director director = (Director) mostrar.readObject();
        // Mostramos por pantalla todos los directores
        try {
            while (director != null) {
                if (Objects.equals(director.getNombre(), nombre)) {
                    director.Mostrar();
                    es++;
                    director = (Director) mostrar.readObject();
                } else {
                    director = (Director) mostrar.readObject();
                }
            }
            mostrar.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
        if (es == 0) {
            System.out.println("No se ha encontrado a ningun director con ese nombre");
        } else {
            es = 0;
        }
        Main.Directores();
    }

    public void Array_Directores() throws IOException, ClassNotFoundException {
        // ObjectInputStream para insertar los actores en el array peliculas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Directores.dat"));
        Director director = (Director) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (director != null) {
                directores.add(director);
                director = (Director) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        mostrar.close();
    }


    // Insertar directores

    public void Insertar_Directores() throws IOException, ClassNotFoundException {
        String nombre = "";
        String edad;

        System.out.println("Insertar director");
        try {
            System.out.print("Nombre del director: ");
            nombre = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Dato introducido erroneo");
        }
        try {
            System.out.println("Edad del director (En caso de estar vivo, introduzca su edad. En caso contrario, año de nacimiento y muerte separadas de un guion): ");
            edad = scanner.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Directores.dat"));
        Director director = (Director) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (director != null) {
                ultimo_id = director.getId_director();
                director = (Director) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
        mostrar.close();

        Director dir = new Director(ultimo_id+1, nombre, edad);
        directores.add(dir);

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Directores.dat"));
        int i_d = 0;
        for (Director d : directores) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Directores();
    }

    // Borrar directores

    public void Eliminar_Directores() throws IOException, ClassNotFoundException {
        int id = -1;

        System.out.println("Insertar director");

        Mostrar_Directores("1");

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Directores.dat"));
        Director actor = (Director) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (actor != null) {
                ultimo_id = actor.getId_director();
                actor = (Director) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar.close();

        do {
            try {
                System.out.print("ID del director a eliminar: ");
                id = scanner.nextInt();
                if (id <= 0 || id > ultimo_id){
                    System.out.println("Dato introducido erroneo");
                }
            } catch (Exception e) {
                System.out.println("Dato introducido erroneo");
            }
        } while (id <= 0 || id > ultimo_id);

        boolean s = false;
        for (Director d: directores){
            if (d.getId_director() == id){
                directores.remove(d);
                s = true;
                break;
            }
        }

        if (s){
            System.out.println("borrado completado");
        } else {
            System.out.println("Error en el borrado");
        }

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Directores.dat"));
        int i_d = 0;
        for (Director d : directores) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Directores();


    }

    // FOTÓGRAFOS
    // Mostrar todos los fotógrafos

    public void Mostrar_Fotografos(String s) throws IOException, ClassNotFoundException {
        // ObjectInputStream para mostrar por pantalla los fotografos
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Fotografos.dat"));
        Fotografo fotografo = (Fotografo) mostrar.readObject();
        // Mostramos por pantalla todos los fotografos
        try {
            while (fotografo != null) {
                fotografo.Mostrar();
                fotografo = (Fotografo) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }
        if (s == null) {
            Main.Fotografos();
        }
    }

    // Mostrar fotógrafo por nombre

    public void Mostrar_Fotografo_por_Nombre() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar al fotógrafo por su nombre");

        Mostrar_Fotografos("1");

        try {
            System.out.println("Nombre del fotógrafo: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla los fotógrafos
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Fotografos.dat"));
        Fotografo fotografo = (Fotografo) mostrar.readObject();
        // Mostramos por pantalla todos los directores
        try {
            while (fotografo != null) {
                if (Objects.equals(fotografo.getNombre(), nombre)) {
                    fotografo.Mostrar();
                    es++;
                    fotografo = (Fotografo) mostrar.readObject();
                } else {
                    fotografo = (Fotografo) mostrar.readObject();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        if (es == 0) {
            System.out.println("No se ha encontrado a ningún fotógrafo con ese nombre");
        } else {
            es = 0;
        }
        Main.Fotografos();
    }

    public void Array_Fotografo() throws IOException, ClassNotFoundException {
        // ObjectInputStream para insertar los actores en el array peliculas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Fotografos.dat"));
        Fotografo fotografo = (Fotografo) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (fotografo != null) {
                fotografos.add(fotografo);
                fotografo = (Fotografo) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        mostrar.close();
    }


    // Insertar fotógrafos

    public void Insertar_Fotografo() throws IOException, ClassNotFoundException {
        String nombre = "";
        String edad;

        System.out.println("Insertar fotógrafo");
        try {
            System.out.print("Nombre del fotógrafo: ");
            nombre = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Dato introducido erróneo");
        }
        try {
            System.out.println("Edad del fotógrafo (En caso de estar vivo, introduzca su edad. En caso contrario, año de nacimiento y muerte separadas de un guion): ");
            edad = scanner.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Fotografos.dat"));
        Fotografo fotografo = (Fotografo) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (fotografo != null) {
                ultimo_id = fotografo.id_fotografo;
                fotografo = (Fotografo) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar.close();

        Fotografo fot = new Fotografo(ultimo_id+1, nombre, edad);
        fotografos.add(fot);

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Fotografos.dat"));
        int i_d = 0;
        for (Fotografo d : fotografos) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Fotografos();
    }

    public void Eliminar_Fotografo() throws IOException, ClassNotFoundException {
        int id = -1;

        System.out.println("Eliminar fotografo");

        Mostrar_Fotografos("1");

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Fotografos.dat"));
        Fotografo actor = (Fotografo) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (actor != null) {
                ultimo_id = actor.getId_fotografo();
                actor = (Fotografo) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar.close();

        do {
            try {
                System.out.print("ID del fotografo a eliminar: ");
                id = scanner.nextInt();
                if (id <= 0 || id > ultimo_id){
                    System.out.println("Dato introducido erroneo");
                }
            } catch (Exception e) {
                System.out.println("Dato introducido erroneo");
            }
        } while (id <= 0 || id > ultimo_id);

        boolean s = false;
        for (Fotografo d: fotografos){
            if (d.getId_fotografo() == id){
                fotografos.remove(d);
                s = true;
                break;
            }
        }

        if (s){
            System.out.println("borrado completado");
        } else {
            System.out.println("Error en el borrado");
        }

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Fotografos.dat"));
        int i_d = 0;
        for (Fotografo d : fotografos) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Fotografos();


    }

    // MÚSICOS
    // Mostrar todos los compositores

    public void Mostrar_Musicos(String s) throws IOException, ClassNotFoundException {
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
        if (s == null){
            mostrar.close();
            Main.Musicos();
        }
    }

    // Mostrar músico por nombre

    public void Mostrar_Musico_por_Nombre() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar al compositor por su nombre");

        Mostrar_Musicos("1");

        try {
            System.out.println("Nombre del compositor: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla los compositores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Compositores.dat"));
        Musico musico = (Musico) mostrar.readObject();
        // Mostramos por pantalla todos los compositores
        try {
            while (musico != null) {
                if (Objects.equals(musico.getNombre(), nombre)) {
                    musico.Mostrar();
                    es++;
                    musico = (Musico) mostrar.readObject();
                } else {
                    musico = (Musico) mostrar.readObject();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        if (es == 0) {
            System.out.println("No se ha encontrado a ningún compositores con ese nombre");
        } else {
            es = 0;
        }
        Main.Musicos();
    }

    public void Array_Musico() throws IOException, ClassNotFoundException {
        // ObjectInputStream para insertar los actores en el array peliculas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Compositores.dat"));
        Musico musico = (Musico) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (musico != null) {
                musicos.add(musico);
                musico = (Musico) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        mostrar.close();
    }

    // Insertar músico

    public void Insertar_Musico() throws IOException, ClassNotFoundException {
        String nombre = "";
        String edad;

        System.out.println("Insertar compositor");
        try {
            System.out.print("Nombre del compositor: ");
            nombre = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Dato introducido erróneo");
        }
        try {
            System.out.println("Edad del compositor (En caso de estar vivo, introduzca su edad. En caso contrario, año de nacimiento y muerte separadas de un guion): ");
            edad = scanner.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Compositores.dat"));
        Musico musico = (Musico) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (musico != null) {
                ultimo_id = musico.id_musico;
                musico = (Musico) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar.close();

        Musico mus = new Musico(ultimo_id+1, nombre, edad);
        musicos.add(mus);

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Compositores.dat"));
        int i_d = 0;
        for (Musico d : musicos) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Musicos();
    }

    public void Eliminar_Musicos() throws IOException, ClassNotFoundException {
        int id = -1;

        System.out.println("Eliminar Compositor");

        Mostrar_Musicos("1");

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Compositores.dat"));
        Musico actor = (Musico) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (actor != null) {
                ultimo_id = actor.getId_musico();
                actor = (Musico) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar.close();

        do {
            try {
                System.out.print("ID del compositor a eliminar: ");
                id = scanner.nextInt();
                if (id <= 0 || id > ultimo_id){
                    System.out.println("Dato introducido erroneo");
                }
            } catch (Exception e) {
                System.out.println("Dato introducido erroneo");
            }
        } while (id <= 0 || id > ultimo_id);

        boolean s = false;
        for (Musico d: musicos){
            if (d.getId_musico() == id){
                musicos.remove(d);
                s = true;
                break;
            }
        }

        if (s){
            System.out.println("borrado completado");
        } else {
            System.out.println("Error en el borrado");
        }

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Compositores.dat"));
        int i_d = 0;
        for (Musico d : musicos) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Musicos();


    }

    // ACTORES
    // Mostrar todos los actores

    public void Mostrar_Actores(String s) throws IOException, ClassNotFoundException {
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
        if (s == null) {
            Main.Actores();
        }
    }

    // Mostrar actor por nombre

    public void Mostrar_Actor_por_Nombre() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar al actor por su nombre");

        Mostrar_Actores("1");

        try {
            System.out.println("Nombre del actor: ");
            nombre = scanner.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla los actores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Actores.dat"));
        Actor actor = (Actor) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (actor != null) {
                if (Objects.equals(actor.getNombre(), nombre)) {
                    actor.Mostrar();
                    es++;
                    actor = (Actor) mostrar.readObject();
                } else {
                    actor = (Actor) mostrar.readObject();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        if (es == 0) {
            System.out.println("No se ha encontrado a ningún actor con ese nombre");
        } else {
            es = 0;
        }
        Main.Actores();
    }

    public void Array_Actores() throws IOException, ClassNotFoundException {
        // ObjectInputStream para insertar los actores en el array peliculas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Actores.dat"));
        Actor actor = (Actor) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (actor != null) {
                actores.add(actor);
                actor = (Actor) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        mostrar.close();
    }

    // Insertar actor

    public void Insertar_Actor() throws IOException, ClassNotFoundException {
        String nombre = "";
        String edad;

        System.out.println("Insertar actor");
        try {
            System.out.print("Nombre del actor: ");
            nombre = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Dato introducido erróneo");
        }
        try {
            System.out.println("Edad del actor (En caso de estar vivo, introduzca su edad. En caso contrario, año de nacimiento y muerte separadas de un guion): ");
            edad = scanner.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Actores.dat"));
        Actor actor = (Actor) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (actor != null) {
                ultimo_id = actor.id_actor;
                actor = (Actor) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar.close();

        Actor act = new Actor(ultimo_id+1, nombre, edad);
        actores.add(act);

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Actores.dat"));
        int i_d = 0;
        for (Actor d : actores) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Actores();
    }

    public void Eliminar_Actores() throws IOException, ClassNotFoundException {
        int id = -1;

        System.out.println("Eliminar actores");

        Mostrar_Actores("1");

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Actores.dat"));
        Actor actor = (Actor) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (actor != null) {
                ultimo_id = actor.id_actor;
                actor = (Actor) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar.close();

        do {
            try {
                System.out.print("ID del actor a eliminar: ");
                id = scanner.nextInt();
                if (id <= 0 || id > ultimo_id){
                    System.out.println("Dato introducido erroneo");
                }
            } catch (Exception e) {
                System.out.println("Dato introducido erroneo");
            }
        } while (id <= 0 || id > ultimo_id);

        boolean s = false;
        for (Actor d: actores){
            if (d.getId_actor() == id){
                actores.remove(d);
                s = true;
                break;
            }
        }

        if (s){
            System.out.println("borrado completado");
        } else {
            System.out.println("Error en el borrado");
        }

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Actores.dat"));
        int i_d = 0;
        for (Actor d : actores) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Actores();


    }

    // PELÍCULAS
    // Mostrar todas las peliculas

    public void Mostrar_Peliculas(String s) throws IOException, ClassNotFoundException {
        // ObjectInputStream para mostrar por pantalla los actores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();
        // Mostramos por pantalla todos los actores
        try {
            while (pelicula != null) {
                pelicula.Mostrar();
                pelicula = (Pelicula) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algun error con la clase");
        }

        mostrar.close();

        if (s == null) {
            Main.Peliculas();
        }
    }

    // Mostrar la pelicula con el nombre

    public void Mostrar_Pelicula_por_Nombre() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar la película por su nombre");

        Mostrar_Peliculas("1");

        try {
            System.out.println("Nombre de la pelicula: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla las películas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();
        // Mostramos por pantalla todas las peliculas
        try {
            while (pelicula != null) {
                if (Objects.equals(pelicula.getNombre_pelicula(), nombre)) {
                    pelicula.Mostrar();
                    es++;
                    pelicula = (Pelicula) mostrar.readObject();
                } else {
                    pelicula = (Pelicula) mostrar.readObject();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        Main.Peliculas();
        if (es == 0) {
            System.out.println("No se ha encontrado a ninguna película con ese nombre");
        } else {
            es = 0;
        }

    }

    // Mostrar la pelicula con el nombre del director

    public void Mostrar_Pelicula_por_Director() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar la película por su director");

        Mostrar_Directores("1");

        try {
            System.out.println("Nombre del director de la película: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla las películas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();
        // Mostramos por pantalla todas las peliculas
        try {
            while (pelicula != null) {
                if (Objects.equals(pelicula.getDirector().getNombre(), nombre)) {
                    pelicula.Mostrar();
                    es++;
                    pelicula = (Pelicula) mostrar.readObject();
                } else {
                    pelicula = (Pelicula) mostrar.readObject();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        if (es == 0) {
            System.out.println("No se ha encontrado a ninguna película con ese nombre");
        } else {
            es = 0;
        }
        Main.Peliculas();
    }

    // Mostrar la pelicula con el nombre del músico

    public void Mostrar_Pelicula_por_Musico() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar la película por su compositor");

        Mostrar_Musicos("1");

        try {
            System.out.println("Nombre del compositor de la película: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla las películas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();
        // Mostramos por pantalla todas las peliculas
        try {
            while (pelicula != null) {
                if (Objects.equals(pelicula.getMusico().getNombre(), nombre)) {
                    pelicula.Mostrar();
                    es++;
                    pelicula = (Pelicula) mostrar.readObject();
                } else {
                    pelicula = (Pelicula) mostrar.readObject();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        if (es == 0) {
            System.out.println("No se ha encontrado a ninguna película con ese nombre");
        } else {
            es = 0;
        }
        Main.Peliculas();
    }

    // Mostrar la película con el nombre del fotógrafo

    public void Mostrar_Pelicula_por_Fotografo() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar la película por su fotógrafo");

        Mostrar_Fotografos("1");

        try {
            System.out.println("Nombre del fotógrafo de la película: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla las películas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();
        // Mostramos por pantalla todas las peliculas
        try {
            while (pelicula != null) {
                if (Objects.equals(pelicula.getFotografo().getNombre(), nombre)) {
                    pelicula.Mostrar();
                    es++;
                    pelicula = (Pelicula) mostrar.readObject();
                } else {
                    pelicula = (Pelicula) mostrar.readObject();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        if (es == 0) {
            System.out.println("No se ha encontrado a ninguna película con ese nombre");
        } else {
            es = 0;
        }
        Main.Peliculas();
    }

    // Mostrar la película con el nombre del actor

    public void Mostrar_Pelicula_por_Actor() throws IOException, ClassNotFoundException {
        String nombre;
        int es = 0;
        System.out.println("Mostrar la película por su actor");

        Mostrar_Peliculas("1");

        try {
            System.out.println("Nombre del actor de la película: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // ObjectInputStream para mostrar por pantalla las películas
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();
        // Mostramos por pantalla todas las peliculas
        try {
            while (pelicula != null) {
                if (Objects.equals(pelicula.getActor_prota().getNombre(), nombre)) {
                    System.out.println("Actor protagonista");
                    pelicula.Mostrar();
                    es++;
                    pelicula = (Pelicula) mostrar.readObject();
                } else {
                    pelicula = (Pelicula) mostrar.readObject();
                }

                if (Objects.equals(pelicula.getActor_secundario().getNombre(), nombre)) {
                    System.out.println("Actor secundario");
                    pelicula.Mostrar();
                    es++;
                    pelicula = (Pelicula) mostrar.readObject();
                } else {
                    pelicula = (Pelicula) mostrar.readObject();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        if (es == 0) {
            System.out.println("No se ha encontrado a ninguna película con ese nombre");
        } else {
            es = 0;
        }
        Main.Peliculas();
    }

    public void Eliminar_Pelicula() throws IOException, ClassNotFoundException {
        int id = -1;

        System.out.println("Eliminar pelicula");

        Mostrar_Peliculas("1");

        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula actor = (Pelicula) mostrar.readObject();
        int ultimo_id = 0;
        try {
            while (actor != null) {
                ultimo_id = actor.getId_pelicula();
                actor = (Pelicula) mostrar.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar.close();

        do {
            try {
                System.out.print("ID de la pelicula a eliminar: ");
                id = scanner.nextInt();
                if (id <= 0 || id > ultimo_id){
                    System.out.println("Dato introducido erroneo");
                }
            } catch (Exception e) {
                System.out.println("Dato introducido erroneo");
            }
        } while (id <= 0 || id > ultimo_id);

        boolean s = false;
        for (Pelicula d: peliculas){
            if (d.getId_pelicula() == id){
                peliculas.remove(d);
                s = true;
                break;
            }
        }

        if (s){
            System.out.println("borrado completado");
        } else {
            System.out.println("Error en el borrado");
        }

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Peliculas.dat"));
        int i_d = 0;
        for (Pelicula d : peliculas) {
            escribir.writeObject(d);
            i_d++;
        }
        escribir.writeObject(null);
        escribir.close();
        Main.Peliculas();


    }

    public void Puntuacion_pelicula() throws IOException, ClassNotFoundException {
        double punt = 0;
        int id = 0;
        double puntuacion = 0;
        String nombre = "";
        int cantidad = 0;
        System.out.println();

        System.out.println("Tu puntuacion de la pelicula");

        Mostrar_Peliculas("1");


        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();

        int es = 0;
        do {
            System.out.println("nombre de la pelicula: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }


            // Mostramos por pantalla todos los actores

            try {
                while (pelicula != null) {
                    if (Objects.equals(pelicula.getNombre_pelicula(), nombre)) {
                        es++;
                        cantidad = pelicula.getCantidad_puntuado();
                        puntuacion = pelicula.getPuntuacion();
                        id = pelicula.getId_pelicula();
                        pelicula = (Pelicula) mostrar.readObject();
                    } else {
                        pelicula = (Pelicula) mostrar.readObject();
                    }
                }
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error");
            } catch (ClassNotFoundException e) {
                System.out.println("Ha habido algún error con la clase");
            }
            mostrar.close();
            if (es == 0) {
                System.out.println("No se ha encontrado a ningúna película con ese nombre");
            }
        } while (es == 0);


        boolean as = true;
        do {
            try {
                System.out.println("Inserta tu puntuacion del 0 al 10: ");
                punt = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Error");
            }
            if (punt < 0 || punt > 10){
                System.out.println("Error. Inserta un valor entre el 0 y el 10");
            } else {
                as = false;
            }
        } while (as);

        Pelicula pelicula1 = peliculas.get(id -1);
        pelicula1.setPuntuacion(punt);
        pelicula1.setCantidad_puntuado(1);

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Peliculas.dat"));

        try {
            for (Pelicula p: peliculas){
                escribir.writeObject(p);
            }
            escribir.writeObject(null);
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero Peliculas.dat");
        }
        escribir.close();
        Main.Peliculas();

        System.out.println("Puntuación realizada");
        Main.Peliculas();

    }

    // Insertar una pelicula

    public void Insertar_pelicula() throws IOException, ClassNotFoundException {
        ObjectInputStream mostrar_peli = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar_peli.readObject();
        int ultimo_id = 0;
        try {
            while (pelicula != null) {
                ultimo_id = pelicula.id_pelicula;
                pelicula = (Pelicula) mostrar_peli.readObject();
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }
        mostrar_peli.close();

        String nombre = "";
        String edad;
        String dire = "";
        Director dir = null;
        String mus = "";
        Musico mu = null;
        String fot = "";
        Fotografo fo = null;
        int ano = 0;
        int duracion = 0;
        String act_pri = "";
        Actor ac_pri = null;
        String act_sec = "";
        Actor ac_sec = null;
        double puntuacion = 0.00;


        System.out.println("Insertar pelicula");
        try {
            System.out.print("Nombre de la pelicula: ");
            nombre = scanner.nextLine();
            if (nombre == ""){
                nombre = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Dato introducido erróneo");
        }
        Mostrar_Directores("1");

        boolean aa = true;
        do {
            System.out.println("");
            System.out.println("Inserta el nombre del director de la pelicula a partir de las mostradas");
            dire = scanner.nextLine();
            ObjectInputStream mostrar_dire = new ObjectInputStream(new FileInputStream("Directores.dat"));
            Director director = (Director) mostrar_dire.readObject();

            try {
                while (director != null) {
                    if (Objects.equals(director.getNombre(), dire)) {
                        dir = director;
                        director = (Director) mostrar_dire.readObject();
                        aa = false;
                    } else {
                        director = (Director) mostrar_dire.readObject();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (aa){
                System.out.println("El director no existe. Vuelve a intentarlo");
            }
            mostrar_dire.close();
        } while (aa);


        Mostrar_Musicos("1");

        boolean bb = true;
        do {
            System.out.println("");
            System.out.println("Inserta el nombre del compositor de la pelicula a partir de las mostradas");
            mus = scanner.nextLine();
            ObjectInputStream mostrar_musi = new ObjectInputStream(new FileInputStream("Compositores.dat"));
            Musico musico = (Musico) mostrar_musi.readObject();

            try {
                while (musico != null){
                    if (Objects.equals(musico.getNombre(), mus)){
                        mu = musico;
                        musico = (Musico) mostrar_musi.readObject();
                        bb = false;
                    } else {
                        musico = (Musico) mostrar_musi.readObject();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (bb){
                System.out.println("El compositor no existe. Vuelve a intentarlo");
            }
            mostrar_musi.close();
        } while (bb);


        Mostrar_Fotografos("1");

        boolean cc = true;
        do {
            System.out.println("");
            System.out.println("Inserta el nombre del fotógrafo de la película a partir de las mostradas");
            fot = scanner.nextLine();
            ObjectInputStream mostrar_foto = new ObjectInputStream(new FileInputStream("Fotografos.dat"));
            Fotografo fotografo = (Fotografo) mostrar_foto.readObject();

            try {
                while (fotografo != null){
                    if (Objects.equals(fotografo.getNombre(), fot)){
                        fo = fotografo;
                        fotografo = (Fotografo) mostrar_foto.readObject();
                        cc = false;
                    } else {
                        fotografo = (Fotografo) mostrar_foto.readObject();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (cc){
                System.out.println("El fotografo no existe. Vuelve a intentarlo");
            }

            mostrar_foto.close();
        } while (cc);

        boolean dd = true;
        do {
            try {
                System.out.print("Año de estreno de la pelicula: ");
                ano = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Dato introducido erróneo");
                dd = false;
            }
        } while (!dd);

        boolean ee = true;
        do {
            try {
                System.out.print("Duración (min) de la pelicula: ");
                duracion = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Dato introducido erróneo");
                ee = false;
            }
        } while (!ee);

        Mostrar_Actores("1");

        boolean ff = true;
        do {
            System.out.println("");
            System.out.println("Inserta el nombre del actor principal de la película a partir de las mostradas");
            act_pri = scanner.nextLine();
            ObjectInputStream mostrar_actor_prin = new ObjectInputStream(new FileInputStream("Actores.dat"));
            Actor actor = (Actor) mostrar_actor_prin.readObject();

            try {
                while (actor != null){
                    if (Objects.equals(actor.getNombre(), act_pri)){
                        ac_pri = actor;
                        actor = (Actor) mostrar_actor_prin.readObject();
                        ff = false;
                    } else {
                        actor = (Actor) mostrar_actor_prin.readObject();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (ff){
                System.out.println("El actor no existe. Vuelve a intentarlo");
            }
            mostrar_actor_prin.close();
        } while (ff);

        boolean gg = true;
        do {
            System.out.println("");
            System.out.println("Inserta el nombre del actor secundario de la película a partir de las mostradas");
            act_sec = scanner.nextLine();
            ObjectInputStream mostrar_actor_sec = new ObjectInputStream(new FileInputStream("Actores.dat"));
            Actor actor2 = (Actor) mostrar_actor_sec.readObject();

            try {
                while (actor2 != null){
                    if (Objects.equals(actor2.getNombre(), act_sec)){
                        ac_sec = actor2;
                        actor2 = (Actor) mostrar_actor_sec.readObject();
                        gg = false;
                    } else {
                        actor2 = (Actor) mostrar_actor_sec.readObject();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (gg){
                System.out.println("El actor no existe. Vuelve a intentarlo");
            }

            mostrar_actor_sec.close();
        } while (gg);

        boolean zz = true;
        do {
            try {
                System.out.print("Puntuación de la pelicula: ");
                puntuacion = scanner.nextDouble();

            } catch (Exception e) {
                System.out.println("Dato introducido erróneo");
                zz = false;
            }
        } while (!zz);


        Pelicula pelicula1 = new Pelicula(ultimo_id+1, nombre, dir, mu, fo, ano, duracion, ac_pri, ac_sec, puntuacion);
        peliculas.add(pelicula1);

        ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream("Peliculas.dat"));

        try {
            for (Pelicula p: peliculas){
                escribir.writeObject(p);
            }
            escribir.writeObject(null);
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero Peliculas.dat");
        }
        Main.Peliculas();
    }

    // XML


    public void Peliculas_XML() throws IOException, ClassNotFoundException {

        // ObjectInputStream para mostrar por pantalla los actores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Peliculas.dat"));
        Pelicula pelicula = (Pelicula) mostrar.readObject();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "Pelis", null);
        document.setXmlVersion("1.0");
        ;

        int o = 0;
        // Mostramos por pantalla todas las peliculas
        try {
            while (pelicula != null) {
                Element raiz = document.createElement("pelicula");
                document.getDocumentElement().appendChild(raiz);
                raiz.setAttribute("id", String.valueOf(pelicula.getId_pelicula()));
                CrearElemento("titulo_pelicula", pelicula.getNombre_pelicula(), raiz, document);
                Element director = document.createElement("director");
                raiz.appendChild(director);
                director.setAttribute("id", String.valueOf(pelicula.getDirector().getId_director()));
                CrearElemento("nombre", pelicula.getDirector().getNombre(), director, document);
                CrearElemento("edad", pelicula.getDirector().getEdad(), director, document);
                Element compositor = document.createElement("compositor");
                raiz.appendChild(compositor);
                compositor.setAttribute("id", String.valueOf(pelicula.getMusico().getId_musico()));
                CrearElemento("nombre", pelicula.getMusico().getNombre(), compositor, document);
                CrearElemento("edad", pelicula.getMusico().getEdad(), compositor, document);
                Element fotografo = document.createElement("fotografo");
                raiz.appendChild(fotografo);
                fotografo.setAttribute("id", String.valueOf(pelicula.getFotografo().getId_fotografo()));
                CrearElemento("nombre", pelicula.getFotografo().getNombre(), fotografo, document);
                CrearElemento("edad", pelicula.getFotografo().getEdad(), fotografo, document);
                CrearElemento("año_estreno", String.valueOf(pelicula.getAno()), raiz, document);
                CrearElemento("duracion", String.valueOf(pelicula.getDuracion()), raiz, document);
                Element actor_pri = document.createElement("actor_principal");
                raiz.appendChild(actor_pri);
                actor_pri.setAttribute("id", String.valueOf(pelicula.getActor_prota().getId_actor()));
                CrearElemento("nombre", pelicula.getActor_prota().getNombre(), actor_pri, document);
                CrearElemento("edad", pelicula.getActor_prota().getEdad(), actor_pri, document);
                Element actor_sec = document.createElement("actor_secundario");
                raiz.appendChild(actor_sec);
                actor_sec.setAttribute("id", String.valueOf(pelicula.getActor_secundario().getId_actor()));
                CrearElemento("nombre", pelicula.getActor_secundario().getNombre(), actor_sec, document);
                CrearElemento("edad", pelicula.getActor_secundario().getEdad(), actor_sec, document);
                CrearElemento("puntuación", String.valueOf(pelicula.getPuntuacion()), raiz, document);
                pelicula = (Pelicula) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }

        mostrar.close();

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("Peliculas.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        Result console= new StreamResult(System.out);

        Main.Peliculas();
    }

    public void Actores_XML() throws IOException, ClassNotFoundException {

        // ObjectInputStream para mostrar por pantalla los actores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Actores.dat"));
        Actor actor = (Actor) mostrar.readObject();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "actores", null);
        document.setXmlVersion("1.0");
        ;

        int o = 0;
        // Mostramos por pantalla todas las peliculas
        try {
            while (actor != null) {
                Element raiz = document.createElement("actor");
                document.getDocumentElement().appendChild(raiz);
                raiz.setAttribute("id", String.valueOf(actor.getId_actor()));
                CrearElemento("nombre", actor.getNombre(), raiz, document);
                CrearElemento("edad",  actor.getEdad(),raiz, document);
                CrearElemento("Nacionalidad","",raiz, document);
                actor = (Actor) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }

        mostrar.close();

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("Actores.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        Result console= new StreamResult(System.out);

        Main.Actores();
    }

    public void Directores_XML() throws IOException, ClassNotFoundException {

        // ObjectInputStream para mostrar por pantalla los actores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Directores.dat"));
        Director director = (Director) mostrar.readObject();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "directores", null);
        document.setXmlVersion("1.0");
        ;

        int o = 0;
        // Mostramos por pantalla todas las peliculas
        try {
            while (director != null) {
                Element raiz = document.createElement("director");
                document.getDocumentElement().appendChild(raiz);
                raiz.setAttribute("id", String.valueOf(director.getId_director()));
                CrearElemento("nombre", director.getNombre(), raiz, document);
                CrearElemento("edad",  director.getEdad(),raiz, document);
                CrearElemento("Nacionalidad","",raiz, document);
                director = (Director) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }

        mostrar.close();

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("Directores.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        Result console= new StreamResult(System.out);

        Main.Directores();
    }

    public void Fotografos_XML() throws IOException, ClassNotFoundException {

        // ObjectInputStream para mostrar por pantalla los actores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Fotografos.dat"));
        Fotografo fotografo = (Fotografo) mostrar.readObject();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "fotografos", null);
        document.setXmlVersion("1.0");
        ;

        int o = 0;
        // Mostramos por pantalla todas las peliculas
        try {
            while (fotografo != null) {
                Element raiz = document.createElement("director");
                document.getDocumentElement().appendChild(raiz);
                raiz.setAttribute("id", String.valueOf(fotografo.getId_fotografo()));
                CrearElemento("nombre", fotografo.getNombre(), raiz, document);
                CrearElemento("edad",  fotografo.getEdad(),raiz, document);
                CrearElemento("Nacionalidad","",raiz, document);
                fotografo = (Fotografo) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }

        mostrar.close();

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("Fotografos.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        Result console= new StreamResult(System.out);

        Main.Fotografos();
    }

    public void Musicos_XML() throws IOException, ClassNotFoundException {

        // ObjectInputStream para mostrar por pantalla los actores
        ObjectInputStream mostrar = new ObjectInputStream(new FileInputStream("Compositores.dat"));
        Musico musico = (Musico) mostrar.readObject();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "compositores", null);
        document.setXmlVersion("1.0");
        ;

        int o = 0;
        // Mostramos por pantalla todas las peliculas
        try {
            while (musico != null) {
                Element raiz = document.createElement("compositor");
                document.getDocumentElement().appendChild(raiz);
                raiz.setAttribute("id", String.valueOf(musico.getId_musico()));
                CrearElemento("nombre", musico.getNombre(), raiz, document);
                CrearElemento("edad",  musico.getEdad(),raiz, document);
                CrearElemento("Nacionalidad","",raiz, document);
                musico = (Musico) mostrar.readObject();
            }
        } catch (
                IOException e) {
            System.out.println("Ha ocurrido un error");
        } catch (ClassNotFoundException e) {
            System.out.println("Ha habido algún error con la clase");
        }

        mostrar.close();

        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("Compositores.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
        Result console= new StreamResult(System.out);

        Main.Musicos();
    }



    static void CrearElemento(String datoEmple, String valor,
                              Element raiz,  Document document)
    {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }


    public void Mail_Peliculas() throws IOException, ClassNotFoundException {
        String correo;

        System.out.println("Enviar XML de Películas");
        System.out.println("Recuerda en crear antes el XML en el menu de películas");
        System.out.println("Correo electrónico al que enviar el XML: ");

        correo = scanner.nextLine();
        if (correo == ""){
            correo = scanner.nextLine();
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enabled", "true");


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("pruebasmarkel8@gmail.com", "qmlowonwdqvidxno");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pruebasmarkel8@gmail.com")); // Correo de donde se envia el correo
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));// Email del que va ha recibir el correo
            message.setSubject("XML");
            message.setText("Fichero XML");


            MimeMultipart multipart = new MimeMultipart();

            MimeBodyPart fichero = new MimeBodyPart();
            fichero.attachFile(new File("Peliculas.xml"));


            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("Fichero XML de las películas", "text/html");

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(fichero);

            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enviado");

        Main.Peliculas();

    }

    public void imagen(){
        try(Viewer viewer = new Viewer("Peliculas.xml")){
            PngViewOptions viewOptions = new PngViewOptions();
            viewer.view(viewOptions);
        }

    }

}
