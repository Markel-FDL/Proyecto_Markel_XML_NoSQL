package org.example;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.*;
//import org.xmldb.api.*;


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


    public void crear_coleccion_exist_db() {

    }

    public void Crear_coleccion() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Class cl = Class.forName(driver); //Cargar del driver
        Database database = (Database) cl.newInstance(); //Instancia de la BD
        DatabaseManager.registerDatabase(database); //Registro del driver
        col = DatabaseManager.getCollection(URI, usu, usuPwd);

        CollectionManagementService mgt = null;
        try {
            mgt = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");
            col = mgt.createCollection("xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto");
            col.close();
        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
    }




    public void conectar_exist_db(String ss) throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Class cl = Class.forName(driver); //Cargar del driver
        Database database = (Database) cl.newInstance(); //Instancia de la BD
        DatabaseManager.registerDatabase(database); //Registro del driver
        col = DatabaseManager.getCollection(URI, usu, usuPwd);

        if (col == null) {
            System.out.println(" *** LA COLECCION NO EXISTE. ***");
        }

        File archivo = new File(ss + ".xml");
        if (!archivo.canRead())
            System.out.println("ERROR AL LEER EL FICHERO");
        else {
            assert col != null;
            Resource nuevoRecurso = col.createResource(archivo.getName(),
                    "XMLResource");
            nuevoRecurso.setContent(archivo); //Asignamos el archivo
            col.storeResource(nuevoRecurso); //Lo almacenamos en la colección
        }
        assert col != null;
        col.close();
    }


    public void Crear_fichero_Pelicula() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {

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

        Peliculas_XML();

    }

 /*   public void Array_Peliculas() throws IOException, ClassNotFoundException {
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
*/
    // DIRECTORES
    // Mostrar todos los directores



    public String Max_ID_Pelicula() throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            assert col != null;
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("max(/Pelis/pelicula/@id)");

// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
               return (String) r.getContent();
            }

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        return null;
    }


    public boolean Comprobar_ID_Directores(int ID) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $enp in /directores/director \n" +
                    "return \n" +
                    "if (data($enp/@id) = "+ ID +")\n" +
                    "then\n" +
                    "\"true\"\n" +
                    "else\n" +
                    "()");

// recorrer los datos del recurso.
            if (result.getSize() == 0){
                col.close();
                return false;
            } else {
                col.close();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        return false;
    }

    public boolean Comprobar_ID_Fotografos(int ID) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $enp in /fotografos/fotografo \n" +
                    "return \n" +
                    "if (data($enp/@id) = "+ ID +")\n" +
                    "then\n" +
                    "\"true\"\n" +
                    "else\n" +
                    "()");

// recorrer los datos del recurso.
            if (result.getSize() == 0){
                col.close();
                return false;
            } else {
                col.close();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        return false;
    }

    public boolean Comprobar_ID_Actor(int ID) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $enp in /actores/actor \n" +
                    "return \n" +
                    "if (data($enp/@id) = "+ ID +")\n" +
                    "then\n" +
                    "\"true\"\n" +
                    "else\n" +
                    "()");

// recorrer los datos del recurso.
            if (result.getSize() == 0){
                col.close();
                return false;
            } else {
                col.close();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        return false;
    }

    public boolean Comprobar_ID_Musico(int ID) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $enp in /compositores/compositor \n" +
                    "return \n" +
                    "if (data($enp/@id) = "+ ID +")\n" +
                    "then\n" +
                    "\"true\"\n" +
                    "else\n" +
                    "()");

// recorrer los datos del recurso.
            if (result.getSize() == 0){
                col.close();
                return false;
            } else {
                col.close();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        return false;
    }

    public boolean Comprobar_ID_Pelicula(int ID) throws XMLDBException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $enp in /Pelis/pelicula \n" +
                    "return \n" +
                    "if (data($enp/@id) = "+ ID +")\n" +
                    "then\n" +
                    "\"true\"\n" +
                    "else\n" +
                    "()");

// recorrer los datos del recurso.
            if (result.getSize() == 0){
                col.close();
                return false;
            } else {
                col.close();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        return false;
    }



    public void Mostrar_Directores(String s) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /directores/director\n" +
                    "return\n" +
                    "if (not(//$em/nacionalidad/node()))\n" +
                    "then\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', 'Vacia')\n" +
                    "else\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', data($em/nacionalidad))");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        if (s == null) {
            Main.Directores();
        }

    }

    // Mostrar director por nombre

    public void Mostrar_Director_por_Nombre() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Directores("1");

        do {
            try {
                System.out.println("ID del director: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Directores(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } while (ID < 0 || !Comprobar_ID_Directores(ID));


        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /directores/director\n" +
                    "where data($em/@id) = " + ID + " \n" +
                    "return if (not(//$em/nacionalidad/node()))\n" +
                    "then\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', 'Vacia')\n" +
                    "else\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', data($em/nacionalidad))");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();


        Main.Directores();
    }

/*    public void Array_Directores() throws IOException, ClassNotFoundException {
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
*/

    // Insertar directores

    public void Insertar_Directores() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID = -10;
        String nombre = null;
        int edad = -10;
        String nacionalidad = null;

        System.out.println("Insertar un director");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Directores("1");

        try {
            do {
                System.out.println("ID del director: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (Comprobar_ID_Directores(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || Comprobar_ID_Directores(ID));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Nombre del director: ");
                nombre = scanner.nextLine();
                if (Objects.equals(nombre, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(nombre, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Edad del director: ");
                edad = scanner.nextInt();
                scanner.nextLine();
                if (edad < 0) {
                    System.out.println("Error al ingresar la edad");
                }
            } while (edad < 0);

        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Nacionalidad del director: ");
                nacionalidad = scanner.nextLine();
                if (Objects.equals(nacionalidad, "")) {
                    System.out.println("no puedes dejar el campo en blaco");
                }
            } while (Objects.equals(nacionalidad, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update insert\n" +
                    "<director id= '" + ID + "' ><nombre>" + nombre + "</nombre><edad>" + edad + "</edad><nacionalidad>" + nacionalidad + "</nacionalidad>\n" +
                    "\t\t</director>\n" +
                    "into /directores");

            System.out.println("Director almacenado");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }

        assert col != null;
        col.close();

        Main.Directores();
    }

    // Modificar directores

    public void Modificar_Directores() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;
        String campo = null;
        String valor = null;

        System.out.println("Modificar a un director");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Directores("1");

        try {
            do {
                System.out.println("ID del director: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Directores(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || !Comprobar_ID_Directores(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            do {
                System.out.println("Campo a modificar: ");
                campo = scanner.nextLine().toLowerCase();
                if (Objects.equals(campo, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(campo, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Valor a modificar: ");
                valor = scanner.nextLine();
                if (Objects.equals(valor, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(valor, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            try {
                ResourceSet result = servicio.query("update value /directores/director[@id=" + ID + "]/" + campo + "\n" +
                        "with '" + valor + "'");
            } catch (XMLDBException e) {
                System.out.println("Error al insertar algún error");
            }

            System.out.println("Modificación concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        Main.Directores();


    }

    // Borrar directores

    public void Eliminar_Directores() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        System.out.println("Eliminar a un director");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Directores("1");

        try {
            do {
                System.out.println("ID del director: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Directores(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || !Comprobar_ID_Directores(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update delete /directores/director[@id=" + ID + "]");

            System.out.println("Borrado concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        Main.Directores();


    }

    // FOTÓGRAFOS
    // Mostrar todos los fotógrafos

    public void Mostrar_Fotografos(String s) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /fotografos/fotografo return if (not(//$em/nacionalidad/node()))\n" +
                    "then\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', 'Vacia')\n" +
                    "else\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', data($em/nacionalidad))");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        if (s == null){
            Main.Fotografos();
        }

    }

    // Mostrar fotógrafo por nombre

    public void Mostrar_Fotografo_por_Nombre() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Fotografos("1");

        try {
            do {
                System.out.println("ID del fotografo: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Fotografos(ID)) {
                    System.out.println("Error al mostrar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || !Comprobar_ID_Fotografos(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $enp in /fotografos/fotografo\n" +
                    "where data($enp/@id) = " + ID + " \n" +
                    "return if (not(//$enp/nacionalidad/node()))\n" +
                    "then\n" +
                    "concat('ID: ', data($enp/@id), ' Nombre: ', data($enp/nombre), ' Edad: ', data($enp/edad), ' Nacionalidad: ', 'Vacia')\n" +
                    "else\n" +
                    "concat('ID: ', data($enp/@id), ' Nombre: ', data($enp/nombre), ' Edad: ', data($enp/edad), ' Nacionalidad: ', data($enp/nacionalidad))");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Fotografos();
    }

  /*  public void Array_Fotografo() throws IOException, ClassNotFoundException {
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
*/

    // Insertar fotógrafos

    public void Insertar_Fotografo() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID = -10;
        String nombre = null;
        int edad = -10;
        String nacionalidad = null;

        System.out.println("Insertar un fotografo");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Fotografos("1");

        try {
            do {
                System.out.println("ID del fotografo: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (Comprobar_ID_Fotografos(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || Comprobar_ID_Fotografos(ID));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Nombre del fotografo: ");
                nombre = scanner.nextLine();
                if (Objects.equals(nombre, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(nombre, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Edad del fotógrafo: ");
                edad = scanner.nextInt();
                scanner.nextLine();
                if (edad < 0) {
                    System.out.println("Error al ingresar la edad");
                }
            } while (edad < 0);

        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Nacionalidad del fotógrafo: ");
                nacionalidad = scanner.nextLine();
                if (Objects.equals(nacionalidad, "")) {
                    System.out.println("no puedes dejar el campo en blaco");
                }
            } while (Objects.equals(nacionalidad, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update insert\n" +
                    "<fotografo id= '" + ID + "' ><nombre>" + nombre + "</nombre><edad>" + edad + "</edad><nacionalidad>" + nacionalidad + "</nacionalidad>\n" +
                    "\t\t</fotografo>\n" +
                    "into /fotografos");

            System.out.println("Fotografo almacenado");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }

        assert col != null;
        col.close();

        Main.Fotografos();
    }

    // Modificar fotografo

    public void Modificar_Fotografos() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;
        String campo = null;
        String valor = null;

        System.out.println("Modificar a un fotógrafo");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Fotografos("1");

        try {
            do {
                System.out.println("ID del fotografo: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Fotografos(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || !Comprobar_ID_Fotografos(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            do {
                System.out.println("Campo a modificar: ");
                campo = scanner.nextLine().toLowerCase();
                if (Objects.equals(campo, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(campo, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Valor a modificar: ");
                valor = scanner.nextLine();
                if (Objects.equals(valor, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(valor, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            try {
                ResourceSet result = servicio.query("update value /fotografos/fotografo[@id=" + ID + "]/" + campo + "\n" +
                        "with '" + valor + "'");
            } catch (XMLDBException e) {
                System.out.println("Error al insertar algún error");
            }

            System.out.println("Modificación concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        Main.Fotografos();


    }

    public void Eliminar_Fotografo() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        System.out.println("Eliminar a un fotógrafo");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Fotografos("1");

        try {
            do {
                System.out.println("ID del fotógrafo: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Fotografos(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || !Comprobar_ID_Fotografos(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update delete /fotografos/fotografo[@id=" + ID + "]");

            System.out.println("Borrado concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Fotografos();
    }

    // MÚSICOS
    // Mostrar todos los compositores

    public void Mostrar_Musicos(String s) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /compositores/compositor return if (not(//$em/nacionalidad/node()))\n" +
                    "then\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', 'Vacia')\n" +
                    "else\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', data($em/nacionalidad))");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        if (Objects.equals(s, null)){
            Main.Musicos();
        }
    }

    // Mostrar músico por nombre

    public void Mostrar_Musico_por_Nombre() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Musicos("1");

        try {
            do {
                System.out.println("ID del compositor: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Musico(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Musico(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /compositores/compositor\n" +
                    "where data($em/@id) = " + ID + " \n" +
                    "return if (not(//$em/nacionalidad/node()))\n" +
                    "then\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', 'Vacia')\n" +
                    "else\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', data($em/nacionalidad))");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Musicos();
    }

 /*   public void Array_Musico() throws IOException, ClassNotFoundException {
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
*/
    // Insertar músico

    public void Insertar_Musico() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID = -10;
        String nombre = null;
        int edad = -10;
        String nacionalidad = null;

        System.out.println("Insertar un compositor");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Musicos("1");

        try {
            do {
                System.out.println("ID del compositor: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (Comprobar_ID_Musico(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || Comprobar_ID_Musico(ID));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Nombre del compositor: ");
                nombre = scanner.nextLine();
                if (Objects.equals(nombre, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(nombre, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Edad del compositor: ");
                edad = scanner.nextInt();
                scanner.nextLine();
                if (edad < 0) {
                    System.out.println("Error al ingresar la edad");
                }
            } while (edad < 0);

        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Nacionalidad del compositor: ");
                nacionalidad = scanner.nextLine();
                if (Objects.equals(nacionalidad, "")) {
                    System.out.println("no puedes dejar el campo en blaco");
                }
            } while (Objects.equals(nacionalidad, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update insert\n" +
                    "<compositor id= '" + ID + "' ><nombre>" + nombre + "</nombre><edad>" + edad + "</edad><nacionalidad>" + nacionalidad + "</nacionalidad>\n" +
                    "\t\t</compositor>\n" +
                    "into /compositores");

            System.out.println("Compositor almacenado");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }

        assert col != null;
        col.close();
        Main.Musicos();
    }

    // Modificar musico

    public void Modificar_Musico() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;
        String campo = null;
        String valor = null;

        System.out.println("Modificar a un compositor");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Musicos("1");

        try {
            do {
                System.out.println("ID del compositor: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Musico(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Musico(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            do {
                System.out.println("Campo a modificar: ");
                campo = scanner.nextLine().toLowerCase();
                if (Objects.equals(campo, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(campo, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Valor a modificar: ");
                valor = scanner.nextLine();
                if (Objects.equals(valor, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(valor, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            try {
                ResourceSet result = servicio.query("update value /compositores/compositor[@id=" + ID + "]/" + campo + "\n" +
                        "with '" + valor + "'");
            } catch (XMLDBException e) {
                System.out.println("Error al insertar algún error");
            }

            System.out.println("Modificación concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        Main.Musicos();


    }

    public void Eliminar_Musicos() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        System.out.println("Eliminar a un compositor");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Musicos("1");

        try {
            do {
                System.out.println("ID del compositor: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Musico(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Musico(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update delete /compositores/compositor[@id=" + ID + "]");

            System.out.println("Borrado concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Musicos();

    }

    // ACTORES
    // Mostrar todos los actores

    public void Mostrar_Actores(String s) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $em in /actores/actor return if (not(//$em/nacionalidad/node()))\n" +
                    "then\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', 'Vacia')\n" +
                    "else\n" +
                    "concat('ID: ', data($em/@id), ' Nombre: ', data($em/nombre), ' Edad: ', data($em/edad), ' Nacionalidad: ', data($em/nacionalidad))");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        if (Objects.equals(s, null)){
            Main.Actores();
        }

    }

    // Mostrar actor por nombre

    public void Mostrar_Actor_por_Nombre() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Actores("1");

        try {
            do {
                System.out.println("ID del actor: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Actor(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /actores/actor\n" +
                    "where data($emp/@id) = " + ID + " \n" +
                    "return if (not(//$emp/nacionalidad/node()))\n" +
                    "then\n" +
                    "concat('ID: ', data($emp/@id), ' Nombre: ', data($emp/nombre), ' Edad: ', data($emp/edad), ' Nacionalidad: ', 'Vacia')\n" +
                    "else\n" +
                    "concat('ID: ', data($emp/@id), ' Nombre: ', data($emp/nombre), ' Edad: ', data($emp/edad), ' Nacionalidad: ', data($emp/nacionalidad))");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Actores();
    }

 /*   public void Array_Actores() throws IOException, ClassNotFoundException {
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
*/
    // Insertar actor

    public void Insertar_Actor() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID = -10;
        String nombre = null;
        int edad = -10;
        String nacionalidad = null;

        System.out.println("Insertar un actor");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Actores("1");

        try {
            do {
                System.out.println("ID del actor: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (Comprobar_ID_Actor(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || Comprobar_ID_Actor(ID));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Nombre del actor: ");
                nombre = scanner.nextLine();
                if (Objects.equals(nombre, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(nombre, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Edad del actor: ");
                edad = scanner.nextInt();
                scanner.nextLine();
                if (edad < 0) {
                    System.out.println("Error al ingresar la edad");
                }
            } while (edad < 0);

        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Nacionalidad del actor: ");
                nacionalidad = scanner.nextLine();
                if (Objects.equals(nacionalidad, "")) {
                    System.out.println("no puedes dejar el campo en blaco");
                }
            } while (Objects.equals(nacionalidad, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update insert\n" +
                    "<actor id= '" + ID + "' ><nombre>" + nombre + "</nombre><edad>" + edad + "</edad><nacionalidad>" + nacionalidad + "</nacionalidad>\n" +
                    "\t\t</actor>\n" +
                    "into /actores");

            System.out.println("Actor almacenado");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }

        assert col != null;
        col.close();
        Main.Actores();
    }

    // Modificar actor

    public void Modificar_Actor() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;
        String campo = null;
        String valor = null;

        System.out.println("Modificar a un actor");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Actores("1");

        try {
            do {
                System.out.println("ID del actor: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Actor(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            do {
                System.out.println("Campo a modificar: ");
                campo = scanner.nextLine().toLowerCase();
                if (Objects.equals(campo, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(campo, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            do {
                System.out.println("Valor a modificar: ");
                valor = scanner.nextLine();
                if (Objects.equals(valor, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(valor, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            try {
                ResourceSet result = servicio.query("update value /actores/actor[@id=" + ID + "]/" + campo + "\n" +
                        "with '" + valor + "'");
            } catch (XMLDBException e) {
                System.out.println("Error al insertar algún error");
            }

            System.out.println("Modificación concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        Main.Actores();


    }

    public void Eliminar_Actores() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        System.out.println("Eliminar a un actor");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Actores("1");

        try {
            do {
                System.out.println("ID del actor: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || !Comprobar_ID_Actor(ID));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update delete /actores/actor[@id=" + ID + "]");

            System.out.println("Borrado concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Actores();
    }

    // PELÍCULAS
    // Mostrar todas las peliculas

    public void Mostrar_Peliculas(String s) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /Pelis/pelicula\n" +
                    "let $id:= data($emp/@id)\n" +
                    "let $titulo:= $emp/titulo_pelicula\n" +
                    "let $id_director:= data($emp/director/@id)\n" +
                    "let $nombre_director:= (/directores/director[@id=$id_director]/nombre)\n" +
                    "let $id_compositor:= data($emp/compositor/@id)\n" +
                    "let $nombre_compositor:= (/compositores/compositor[@id=$id_compositor]/nombre)\n" +
                    "let $id_fotografo:= data($emp/fotografo/@id)\n" +
                    "let $nombre_fotografo:= (/fotografos/fotografo[@id=$id_fotografo]/nombre)\n" +
                    "let $ano:= $emp/año_estreno\n" +
                    "let $duracion:= $emp/duracion\n" +
                    "let $actor_pri:= data($emp/actor_principal/@id)\n" +
                    "let $nombre_actor_pri:= (/actores/actor[@id=$actor_pri]/nombre)\n" +
                    "let $actor_sec:= data($emp/actor_secundario/@id)\n" +
                    "let $nombre_actor_sec:= (/actores/actor[@id=$actor_sec]/nombre)\n" +
                    "let $puntuacion:= $emp/puntuación\n" +
                    "return \n" +
                    "concat('ID: ', $id, ' Titulo: ', $titulo, ' Nombre del director: ', data($nombre_director), ' Nombre del compositor: ', data($nombre_compositor), ' Nombre del fotografo: ', data($nombre_fotografo), ' Año de estreno: ', $ano, ' Duracion: ', $duracion, ' Nombre del actor principal: ', data($nombre_actor_pri), ' Nombre del actor secundario: ', data($nombre_actor_sec), ' Puntuacion: ', $puntuacion)");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        if (s == null) {
            Main.Peliculas();
        }
    }

    // Mostrar peliculas por orden de puntuacion

    public void Mostrar_Peliculas_orden_puntuacion(String s) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /Pelis/pelicula\n" +
                    "let $id:= data($emp/@id)\n" +
                    "let $titulo:= $emp/titulo_pelicula\n" +
                    "let $id_director:= data($emp/director/@id)\n" +
                    "let $nombre_director:= (/directores/director[@id=$id_director]/nombre)\n" +
                    "let $id_compositor:= data($emp/compositor/@id)\n" +
                    "let $nombre_compositor:= (/compositores/compositor[@id=$id_compositor]/nombre)\n" +
                    "let $id_fotografo:= data($emp/fotografo/@id)\n" +
                    "let $nombre_fotografo:= (/fotografos/fotografo[@id=$id_fotografo]/nombre)\n" +
                    "let $ano:= $emp/año_estreno\n" +
                    "let $duracion:= $emp/duracion\n" +
                    "let $actor_pri:= data($emp/actor_principal/@id)\n" +
                    "let $nombre_actor_pri:= (/actores/actor[@id=$actor_pri]/nombre)\n" +
                    "let $actor_sec:= data($emp/actor_secundario/@id)\n" +
                    "let $nombre_actor_sec:= (/actores/actor[@id=$actor_sec]/nombre)\n" +
                    "let $puntuacion:= $emp/puntuación\n" +
                    "let $puntuacionn:= max($emp/puntuación)\n" +
                    "order by($puntuacion) descending\n" +
                    "return \n" +
                    "concat('ID: ', $id\n, ' Titulo: ', $titulo\n, ' Nombre del director: ', data($nombre_director)\n, ' Nombre del compositor: ', data($nombre_compositor)\n, ' Nombre del fotografo: ', data($nombre_fotografo)\n, ' Año de estreno: ', $ano\n, ' Duracion: ', $duracion\n, ' Nombre del actor principal: ', data($nombre_actor_pri)\n, ' Nombre del actor secundario: ', data($nombre_actor_sec)\n, ' Puntuacion: ', $puntuacion)\n");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    // Mostrar la pelicula con el nombre

    public void Mostrar_Pelicula_por_ID() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Peliculas("1");

        try {
            do {
                System.out.println("ID de la película: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Pelicula(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            } while (ID < 0 || !Comprobar_ID_Pelicula(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /Pelis/pelicula\n" +
                    "let $id:= data($emp/@id)\n" +
                    "let $titulo:= $emp/titulo_pelicula\n" +
                    "let $id_director:= data($emp/director/@id)\n" +
                    "let $nombre_director:= (/directores/director[@id=$id_director]/nombre)\n" +
                    "let $id_compositor:= data($emp/compositor/@id)\n" +
                    "let $nombre_compositor:= (/compositores/compositor[@id=$id_compositor]/nombre)\n" +
                    "let $id_fotografo:= data($emp/fotografo/@id)\n" +
                    "let $nombre_fotografo:= (/fotografos/fotografo[@id=$id_fotografo]/nombre)\n" +
                    "let $ano:= $emp/año_estreno\n" +
                    "let $duracion:= $emp/duracion\n" +
                    "let $actor_pri:= data($emp/actor_principal/@id)\n" +
                    "let $nombre_actor_pri:= (/actores/actor[@id=$actor_pri]/nombre)\n" +
                    "let $actor_sec:= data($emp/actor_secundario/@id)\n" +
                    "let $nombre_actor_sec:= (/actores/actor[@id=$actor_sec]/nombre)\n" +
                    "let $puntuacion:= $emp/puntuacion\n" +
                    "where data($emp/@id) = " + ID + "\n" +
                    "return \n" +
                    "concat('ID: ', $id, ' Titulo: ', $titulo, ' Nombre del director: ', data($nombre_director), ' Nombre del compositor: ', data($nombre_compositor), ' Nombre del fotografo: ', data($nombre_fotografo), ' Año de estreno: ', $ano, ' Duracion: ', $duracion, ' Nombre del actor principal: ', data($nombre_actor_pri), ' Nombre del actor secundario: ', data($nombre_actor_sec), ' Puntuacion: ', $puntuacion)");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    // Mostrar la pelicula con el nombre del director

    public void Mostrar_Pelicula_por_Director() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Directores("1");

        try {
            do {
                System.out.println("ID del director: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Directores(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Directores(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /Pelis/pelicula\n" +
                    "let $id:= data($emp/@id)\n" +
                    "let $titulo:= $emp/titulo_pelicula\n" +
                    "let $id_director:= data($emp/director/@id)\n" +
                    "let $nombre_director:= (/directores/director[@id=$id_director]/nombre)\n" +
                    "let $id_compositor:= data($emp/compositor/@id)\n" +
                    "let $nombre_compositor:= (/compositores/compositor[@id=$id_compositor]/nombre)\n" +
                    "let $id_fotografo:= data($emp/fotografo/@id)\n" +
                    "let $nombre_fotografo:= (/fotografos/fotografo[@id=$id_fotografo]/nombre)\n" +
                    "let $ano:= $emp/año_estreno\n" +
                    "let $duracion:= $emp/duracion\n" +
                    "let $actor_pri:= data($emp/actor_principal/@id)\n" +
                    "let $nombre_actor_pri:= (/actores/actor[@id=$actor_pri]/nombre)\n" +
                    "let $actor_sec:= data($emp/actor_secundario/@id)\n" +
                    "let $nombre_actor_sec:= (/actores/actor[@id=$actor_sec]/nombre)\n" +
                    "let $puntuacion:= $emp/puntuacion\n" +
                    "where data($emp/director/@id) = " + ID + "\n" +
                    "return \n" +
                    "concat('ID: ', $id, ' Titulo: ', $titulo, ' Nombre del director: ', data($nombre_director), ' Nombre del compositor: ', data($nombre_compositor), ' Nombre del fotografo: ', data($nombre_fotografo), ' Año de estreno: ', $ano, ' Duracion: ', $duracion, ' Nombre del actor principal: ', data($nombre_actor_pri), ' Nombre del actor secundario: ', data($nombre_actor_sec), ' Puntuacion: ', $puntuacion)" );

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    // Mostrar la pelicula con el nombre del músico

    public void Mostrar_Pelicula_por_Musico() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Musicos("1");

        try {
            do {
                System.out.println("ID del musico: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Musico(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Musico(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /Pelis/pelicula\n" +
                    "let $id:= data($emp/@id)\n" +
                    "let $titulo:= $emp/titulo_pelicula\n" +
                    "let $id_director:= data($emp/director/@id)\n" +
                    "let $nombre_director:= (/directores/director[@id=$id_director]/nombre)\n" +
                    "let $id_compositor:= data($emp/compositor/@id)\n" +
                    "let $nombre_compositor:= (/compositores/compositor[@id=$id_compositor]/nombre)\n" +
                    "let $id_fotografo:= data($emp/fotografo/@id)\n" +
                    "let $nombre_fotografo:= (/fotografos/fotografo[@id=$id_fotografo]/nombre)\n" +
                    "let $ano:= $emp/año_estreno\n" +
                    "let $duracion:= $emp/duracion\n" +
                    "let $actor_pri:= data($emp/actor_principal/@id)\n" +
                    "let $nombre_actor_pri:= (/actores/actor[@id=$actor_pri]/nombre)\n" +
                    "let $actor_sec:= data($emp/actor_secundario/@id)\n" +
                    "let $nombre_actor_sec:= (/actores/actor[@id=$actor_sec]/nombre)\n" +
                    "let $puntuacion:= $emp/puntuacion\n" +
                    "where data($emp/compositores/@id) = " + ID + "\n" +
                    "return \n" +
                    "concat('ID: ', $id, ' Titulo: ', $titulo, ' Nombre del director: ', data($nombre_director), ' Nombre del compositor: ', data($nombre_compositor), ' Nombre del fotografo: ', data($nombre_fotografo), ' Año de estreno: ', $ano, ' Duracion: ', $duracion, ' Nombre del actor principal: ', data($nombre_actor_pri), ' Nombre del actor secundario: ', data($nombre_actor_sec), ' Puntuacion: ', $puntuacion)");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    // Mostrar la película con el nombre del fotógrafo

    public void Mostrar_Pelicula_por_Fotografo() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Fotografos("1");

        try {
            do {
                System.out.println("ID del fotógrafo: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Fotografos(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Fotografos(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /Pelis/pelicula\n" +
                    "let $id:= data($emp/@id)\n" +
                    "let $titulo:= $emp/titulo_pelicula\n" +
                    "let $id_director:= data($emp/director/@id)\n" +
                    "let $nombre_director:= (/directores/director[@id=$id_director]/nombre)\n" +
                    "let $id_compositor:= data($emp/compositor/@id)\n" +
                    "let $nombre_compositor:= (/compositores/compositor[@id=$id_compositor]/nombre)\n" +
                    "let $id_fotografo:= data($emp/fotografo/@id)\n" +
                    "let $nombre_fotografo:= (/fotografos/fotografo[@id=$id_fotografo]/nombre)\n" +
                    "let $ano:= $emp/año_estreno\n" +
                    "let $duracion:= $emp/duracion\n" +
                    "let $actor_pri:= data($emp/actor_principal/@id)\n" +
                    "let $nombre_actor_pri:= (/actores/actor[@id=$actor_pri]/nombre)\n" +
                    "let $actor_sec:= data($emp/actor_secundario/@id)\n" +
                    "let $nombre_actor_sec:= (/actores/actor[@id=$actor_sec]/nombre)\n" +
                    "let $puntuacion:= $emp/puntuacion\n" +
                    "where data($emp/fotografo/@id) = " + ID + "\n" +
                    "return \n" +
                    "concat('ID: ', $id, ' Titulo: ', $titulo, ' Nombre del director: ', data($nombre_director), ' Nombre del compositor: ', data($nombre_compositor), ' Nombre del fotografo: ', data($nombre_fotografo), ' Año de estreno: ', $ano, ' Duracion: ', $duracion, ' Nombre del actor principal: ', data($nombre_actor_pri), ' Nombre del actor secundario: ', data($nombre_actor_sec), ' Puntuacion: ', $puntuacion)");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    // Mostrar la película con el nombre del actor

    public void Mostrar_Pelicula_por_Actor_Principal() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Actores("1");

        try {
            do {
                System.out.println("ID del actor principal: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Actor(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /Pelis/pelicula\n" +
                    "let $id:= data($emp/@id)\n" +
                    "let $titulo:= $emp/titulo_pelicula\n" +
                    "let $id_director:= data($emp/director/@id)\n" +
                    "let $nombre_director:= (/directores/director[@id=$id_director]/nombre)\n" +
                    "let $id_compositor:= data($emp/compositor/@id)\n" +
                    "let $nombre_compositor:= (/compositores/compositor[@id=$id_compositor]/nombre)\n" +
                    "let $id_fotografo:= data($emp/fotografo/@id)\n" +
                    "let $nombre_fotografo:= (/fotografos/fotografo[@id=$id_fotografo]/nombre)\n" +
                    "let $ano:= $emp/año_estreno\n" +
                    "let $duracion:= $emp/duracion\n" +
                    "let $actor_pri:= data($emp/actor_principal/@id)\n" +
                    "let $nombre_actor_pri:= (/actores/actor[@id=$actor_pri]/nombre)\n" +
                    "let $actor_sec:= data($emp/actor_secundario/@id)\n" +
                    "let $nombre_actor_sec:= (/actores/actor[@id=$actor_sec]/nombre)\n" +
                    "let $puntuacion:= $emp/puntuacion\n" +
                    "where data($emp/actor_principal/@id) = " + ID + "\n" +
                    "return \n" +
                    "concat('ID: ', $id, ' Titulo: ', $titulo, ' Nombre del director: ', data($nombre_director), ' Nombre del compositor: ', data($nombre_compositor), ' Nombre del fotografo: ', data($nombre_fotografo), ' Año de estreno: ', $ano, ' Duracion: ', $duracion, ' Nombre del actor principal: ', data($nombre_actor_pri), ' Nombre del actor secundario: ', data($nombre_actor_sec), ' Puntuacion: ', $puntuacion)");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    public void Mostrar_Pelicula_por_Actor_Secundario() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Actores("1");

        try {
            do {
                System.out.println("ID del actor secundario: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Actor(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("for $emp in /Pelis/pelicula\n" +
                    "let $id:= data($emp/@id)\n" +
                    "let $titulo:= $emp/titulo_pelicula\n" +
                    "let $id_director:= data($emp/director/@id)\n" +
                    "let $nombre_director:= (/directores/director[@id=$id_director]/nombre)\n" +
                    "let $id_compositor:= data($emp/compositor/@id)\n" +
                    "let $nombre_compositor:= (/compositores/compositor[@id=$id_compositor]/nombre)\n" +
                    "let $id_fotografo:= data($emp/fotografo/@id)\n" +
                    "let $nombre_fotografo:= (/fotografos/fotografo[@id=$id_fotografo]/nombre)\n" +
                    "let $ano:= $emp/año_estreno\n" +
                    "let $duracion:= $emp/duracion\n" +
                    "let $actor_pri:= data($emp/actor_principal/@id)\n" +
                    "let $nombre_actor_pri:= (/actores/actor[@id=$actor_pri]/nombre)\n" +
                    "let $actor_sec:= data($emp/actor_secundario/@id)\n" +
                    "let $nombre_actor_sec:= (/actores/actor[@id=$actor_sec]/nombre)\n" +
                    "let $puntuacion:= $emp/puntuacion\n" +
                    "where data($emp/actor_secundario/@id) = " + ID + "\n" +
                    "return \n" +
                    "concat('ID: ', $id, ' Titulo: ', $titulo, ' Nombre del director: ', data($nombre_director), ' Nombre del compositor: ', data($nombre_compositor), ' Nombre del fotografo: ', data($nombre_fotografo), ' Año de estreno: ', $ano, ' Duracion: ', $duracion, ' Nombre del actor principal: ', data($nombre_actor_pri), ' Nombre del actor secundario: ', data($nombre_actor_sec), ' Puntuacion: ', $puntuacion)");

            System.out.println("Se han obtenido " + result.getSize() + " elementos.");
// recorrer los datos del recurso.
            ResourceIterator i;
            i = result.getIterator();
            if (!i.hasMoreResources())
                System.out.println(" LA CONSULTA NO DEVUELVE NADA O ESTÁ MAL ESCRITA");
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    public void Eliminar_Pelicula() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;

        System.out.println("Eliminar a una película");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Peliculas("1");

        try {
            do {
                System.out.println("ID de la película: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Pelicula(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Pelicula(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update delete /peliculas/pelicula[@id=" + ID + "]");

            System.out.println("Borrado concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    public void Puntuacion_pelicula() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;
        double campo = -10;

        System.out.println("Puntuar una pelicula");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Peliculas("1");

        try {
            do {
                System.out.println("ID de la pelicula: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Actor(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            do {
                System.out.println("Puntuación: ");
                campo = scanner.nextDouble();
                scanner.nextLine();
                if (Objects.equals(campo, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(campo, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            ResourceSet result = servicio.query("update value /Pelis/pelicula[@id="+ ID +"]/puntuación\n" +
                    "with "+ campo +"");

            System.out.println("Modificación concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    // Insertar una pelicula

    public void Insertar_pelicula() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave


        String nombre = "";
        String edad;
        int dire = -10;
        String mus = "";
        int mu = -10;
        String fot = "";
        int fo = -10;
        int ano = 0;
        int duracion = 0;
        String act_pri = "";
        int ac_pri = -10;
        String act_sec = "";
        int ac_sec = -10;
        double puntuacion = 0.00;


        String is = Max_ID_Pelicula();
        int i = Integer.parseInt(is);
        i += 1;


        System.out.println("Insertar pelicula");
        try {
            do {
                System.out.print("Nombre de la pelicula: ");
                nombre = scanner.nextLine();
                if (Objects.equals(nombre, "")) {
                    System.out.println("No puedes dejar el campo vacio");
                }
            }while (Objects.equals(nombre, ""));

        } catch (Exception e) {
            System.out.println("Dato introducido erróneo");
        }


        Mostrar_Directores("1");
        System.out.println("");
        try {
            do {
                System.out.println("Inserta el ID del director de la pelicula a partir de las mostradas");
                dire = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Directores(dire)){
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (dire < 0 || !Comprobar_ID_Directores(dire));

        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }

        Mostrar_Musicos("1");

        System.out.println("");
        try {
            do {
                System.out.println("Inserta el ID del compositor de la pelicula a partir de las mostradas");
                mu = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Musico(mu)){
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (mu < 0 || !Comprobar_ID_Musico(mu));

        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }
        Mostrar_Fotografos("1");

        System.out.println("");
        try {
            do {
                System.out.println("Inserta el ID del fotógrafo de la película a partir de las mostradas");
                fo = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Fotografos(fo)){
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (fo < 0 || !Comprobar_ID_Fotografos(fo));

        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }

        boolean dd = true;
        do {
            try {
                System.out.print("Año de estreno de la pelicula: ");
                ano = scanner.nextInt();
                scanner.nextLine();
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
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Dato introducido erróneo");
                ee = false;
            }
        } while (!ee);

        Mostrar_Actores("1");

        System.out.println("");
        try {
            do {
                System.out.println("Inserta el ID del actor principal de la película a partir de las mostradas");
                ac_pri = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ac_pri)){
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ac_pri < 0 || !Comprobar_ID_Actor(ac_pri));

        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }

        System.out.println("");
        try {
            do {
                System.out.println("Inserta el ID del actor secundario de la película a partir de las mostradas");
                ac_sec = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ac_sec)){
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ac_sec < 0 || !Comprobar_ID_Actor(ac_sec));

        } catch (XMLDBException e) {
            throw new RuntimeException(e);
        }

        boolean zz = true;
        do {
            try {
                System.out.print("Puntuación de la pelicula: ");
                puntuacion = scanner.nextDouble();
                scanner.nextLine();
                scanner.nextLine();
                if (puntuacion == 0.0){
                    System.out.println("Introduce algo");
                }
            } catch (Exception e) {
                System.out.println("Dato introducido erróneo");
                zz = false;
            }
        } while (!zz);

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");

            ResourceSet result = servicio.query ("update insert \n" +
                    "<Pelis><pelicula id= '"+ i +"'><titulo_pelicula>"+ nombre +"</titulo_pelicula><director id= '"+ dire +"'></director><compositor id= '"+ mu +"'></compositor>\n" +
                    "<fotografo id= '"+ fo +"'></fotografo><año_estreno>"+ ano +"</año_estreno><duracion>"+ duracion +"</duracion><actor_principal id= '"+ ac_pri +"'></actor_principal>\n" +
                    "<actor_secundario id= '"+ ac_sec +"'></actor_secundario><puntuacion>"+ puntuacion +"</puntuacion>\n" +
                    "</pelicula></Pelis>\n" +
                    "into /Pelis");


            System.out.println("Película Insertada");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();
        Main.Peliculas();
    }

    public void Modificar_pelicula() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int ID;
        String campo = null;
        String valor = null;

        System.out.println("Modificar una pelicula");

        String driver = "org.exist.xmldb.DatabaseImpl"; //Driver para eXist
        Collection col = null; // Colección
        String URI = "xmldb:exist://localhost:8085/exist/xmlrpc/db/proyecto"; //URI colección
        String usu = "admin"; //Usuario
        String usuPwd = "12345"; //Clave

        Mostrar_Peliculas("1");

        try {
            do {
                System.out.println("ID de la pelicula: ");
                ID = scanner.nextInt();
                scanner.nextLine();
                if (!Comprobar_ID_Actor(ID)) {
                    System.out.println("Error al insertar ID o ya existe el ID insertado");
                }
            }while (ID < 0 || !Comprobar_ID_Actor(ID));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            do {
                System.out.println("Campo a modificar (titulo_pelicula, director, compositor, fotografo, año_estreno, duracion, actor_principal, actor_secundario, puntuación): ");
                campo = scanner.nextLine().toLowerCase();
                if (Objects.equals(campo, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(campo, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        int u = 0;
        switch (campo){
            case "director": u = 1;
                Mostrar_Directores("1");
            break;
            case "compositor": u = 2;
                Mostrar_Musicos("1");
            break;
            case "fotografo": u = 3;
                Mostrar_Fotografos("1");
            break;
            case "año_estreno", "duracion", "titulo_pelicula": u = 4;
            break;
            case "actor_principal": u = 5;
                Mostrar_Actores("1");
            break;
            case "actor_secundario": u = 6;
                Mostrar_Actores("1");
        }

        try {
            do {
                System.out.println("Valor o ID a modificar: ");
                valor = scanner.nextLine();
                if (Objects.equals(valor, "")) {
                    System.out.println("No puedes dejarlo en blanco");
                }
            } while (Objects.equals(valor, ""));
        } catch (Exception e) {
            System.out.println("Error");
        }

        try {
            Class cl = Class.forName(driver); //Cargar del driver
            Database database = (Database) cl.newInstance(); //Instancia de la BD
            DatabaseManager.registerDatabase(database); //Registro del driver

            col = DatabaseManager.getCollection(URI, usu, usuPwd);
            if (col == null)
                System.out.println(" *** LA COLECCION NO EXISTE. ***");
            XPathQueryService servicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            try {
                switch (u){
                    case 1: ResourceSet result = servicio.query("update value /Pelis/pelicula[@id='" + ID + "']/director/@id with '" + valor + "'");
                    break;
                    case 2: ResourceSet result1 = servicio.query("update value /Pelis/pelicula[@id='" + ID + "']/compositor/@id with '" + valor + "'");
                    break;
                    case 3: ResourceSet result2 = servicio.query("update value /Pelis/pelicula[@id='" + ID + "']/fotografo/@id with '" + valor + "'");
                    break;
                    case 4: ResourceSet result3 = servicio.query("update value /Pelis/pelicula[@id=" + ID + "]/" + campo + "\n" +
                            "with '" + valor + "'");
                    break;
                    case 5: ResourceSet result4 = servicio.query("update value /Pelis/pelicula[@id='" + ID + "']/actor_principal/@id with '" + valor + "'");
                    break;
                    case 6: ResourceSet result5 = servicio.query("update value /Pelis/pelicula[@id='" + ID + "']/actor_secundario/@id with '" + valor + "'");
                    break;
                    default:
                        System.out.println("Error al insertar dato");
                }

            } catch (XMLDBException e) {
                System.out.println("Error al insertar algún error");
            }

            System.out.println("Modificación concluido");


        } catch (Exception e) {
            System.out.println("Error al inicializar la BD eXist");
            e.printStackTrace();
        }
        assert col != null;
        col.close();

        Main.Peliculas();


    }

    // XML


    public void Peliculas_XML() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {


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
                //             CrearElemento("nombre", pelicula.getDirector().getNombre(), director, document);
                //             CrearElemento("edad", pelicula.getDirector().getEdad(), director, document);
                Element compositor = document.createElement("compositor");
                raiz.appendChild(compositor);
                compositor.setAttribute("id", String.valueOf(pelicula.getMusico().getId_musico()));
                //             CrearElemento("nombre", pelicula.getMusico().getNombre(), compositor, document);
                //             CrearElemento("edad", pelicula.getMusico().getEdad(), compositor, document);
                Element fotografo = document.createElement("fotografo");
                raiz.appendChild(fotografo);
                fotografo.setAttribute("id", String.valueOf(pelicula.getFotografo().getId_fotografo()));
                //             CrearElemento("nombre", pelicula.getFotografo().getNombre(), fotografo, document);
                //             CrearElemento("edad", pelicula.getFotografo().getEdad(), fotografo, document);
                CrearElemento("año_estreno", String.valueOf(pelicula.getAno()), raiz, document);
                CrearElemento("duracion", String.valueOf(pelicula.getDuracion()), raiz, document);
                Element actor_pri = document.createElement("actor_principal");
                raiz.appendChild(actor_pri);
                actor_pri.setAttribute("id", String.valueOf(pelicula.getActor_prota().getId_actor()));
                //             CrearElemento("nombre", pelicula.getActor_prota().getNombre(), actor_pri, document);
                //             CrearElemento("edad", pelicula.getActor_prota().getEdad(), actor_pri, document);
                Element actor_sec = document.createElement("actor_secundario");
                raiz.appendChild(actor_sec);
                actor_sec.setAttribute("id", String.valueOf(pelicula.getActor_secundario().getId_actor()));
                //             CrearElemento("nombre", pelicula.getActor_secundario().getNombre(), actor_sec, document);
                //             CrearElemento("edad", pelicula.getActor_secundario().getEdad(), actor_sec, document);
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
        Result console = new StreamResult(System.out);

        conectar_exist_db("Peliculas");
    }

    public void Actores_XML() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {

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
                CrearElemento("edad", actor.getEdad(), raiz, document);
                CrearElemento("nacionalidad", "", raiz, document);
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
        Result console = new StreamResult(System.out);

        conectar_exist_db("Actores");
    }

    public void Directores_XML() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {

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
                CrearElemento("edad", director.getEdad(), raiz, document);
                CrearElemento("nacionalidad", "", raiz, document);
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
        Result console = new StreamResult(System.out);

        conectar_exist_db("Directores");
    }

    public void Fotografos_XML() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {

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
                Element raiz = document.createElement("fotografo");
                document.getDocumentElement().appendChild(raiz);
                raiz.setAttribute("id", String.valueOf(fotografo.getId_fotografo()));
                CrearElemento("nombre", fotografo.getNombre(), raiz, document);
                CrearElemento("edad", fotografo.getEdad(), raiz, document);
                CrearElemento("nacionalidad", "", raiz, document);
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
        Result console = new StreamResult(System.out);

        conectar_exist_db("Fotografos");
    }

    public void Musicos_XML() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {

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
                CrearElemento("edad", musico.getEdad(), raiz, document);
                CrearElemento("nacionalidad", "", raiz, document);
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
        Result console = new StreamResult(System.out);

        conectar_exist_db("Compositores");
    }


    static void CrearElemento(String datoEmple, String valor,
                              Element raiz, Document document) {
        Element elem = document.createElement(datoEmple);
        Text text = document.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(text);
    }


    public void Mail_Peliculas() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        String correo;

        System.out.println("Enviar XML de Películas");
        System.out.println("Recuerda en crear antes el XML en el menu de películas");
        System.out.println("Correo electrónico al que enviar el XML: ");

        correo = scanner.nextLine();
        if (correo == "") {
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
                    protected PasswordAuthentication getPasswordAuthentication() {
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

   /* public void imagen(){
        try(Viewer viewer = new Viewer("Peliculas.xml")){
            PngViewOptions viewOptions = new PngViewOptions();
            viewer.view(viewOptions);
        }

    }*/

}
