package org.example;

import org.example.*;
import org.xmldb.api.base.XMLDBException;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static Todo_Funciones_y_Creacion_Fichero_Pelicula funciones = new Todo_Funciones_y_Creacion_Fichero_Pelicula();

    public static void main(String[] args) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        System.out.println("Base de datos sobre peliculas");
        System.out.println("Inicializando aplicación");
        System.out.print("Queres cargar los datos por defecto? (En caso de hacerlo, perderas los datos que hayas añadido)(s para aceptar; otra tecla para rechazar ): ");
        String sn = scanner.next();
        if (Objects.equals(sn, "s") || Objects.equals(sn, "S")) {
            System.out.println("Cargando datos...");
            Creacion_fichero_Actor actor = new Creacion_fichero_Actor();
            Creacion_fichero_Director director = new Creacion_fichero_Director();
            Creacion_fichero_Fotografo fotografo = new Creacion_fichero_Fotografo();
            Creacion_fichero_Musico musico = new Creacion_fichero_Musico();
            funciones.Crear_coleccion();
            actor.creacion_fichero_actor();
            director.creacion_fichero_director();
            fotografo.creacion_fichero_fotografia();
            musico.creacion_fichero_musico();
            funciones.Crear_fichero_Pelicula();
            System.out.println("Carga realizada");
        } else {
/*            funciones.Array_Peliculas();
            funciones.Array_Directores();
            funciones.Array_Fotografo();
            funciones.Array_Musico();
            funciones.Array_Actores();
*/
        }
        Menu_Principal();
    }

    public static void Menu_Principal() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        int num = 0;
        while (num != 6){
            try {
                System.out.println("\nMenu: ");
                System.out.println("1. Películas");
                System.out.println("2. Actores");
                System.out.println("3. Directores");
                System.out.println("4. Fotógrafos");
                System.out.println("5. Compositores");
                System.out.println("6. Salir");
                System.out.print("Selecciona el numero: ");
                num = scanner.nextInt();
                scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Dato erróneo");
        }


        switch (num) {
            case 1 -> Peliculas();
            case 2 -> Actores();
            case 3 -> Directores();
            case 4 -> Fotografos();
            case 5 -> Musicos();
            case 6 -> {
                System.out.println("Fin de la aplicación");
                System.exit(0);
            }
            default -> { System.out.println("Error"); scanner.nextLine();}
        }
        }
    }

    public static void Peliculas() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Todo_Funciones_y_Creacion_Fichero_Pelicula funciones = new Todo_Funciones_y_Creacion_Fichero_Pelicula();
        int num = 0;
        while (num != 14) {
            try {

                System.out.println("\nMenú de las películas");
                System.out.println("1. Mostrar todas las películas");
                System.out.println("2. Mostrar todas las películas por orden de puntuación");
                System.out.println("3. Mostrar por nombre de la película");
                System.out.println("4. Mostrar por nombre del director");
                System.out.println("5. Mostrar por nombre del compositor");
                System.out.println("6. Mostrar por nombre del fotógrafo");
                System.out.println("7. Mostrar por nombre del actor principal");
                System.out.println("8. Mostrar por nombre del actor secundario");
                System.out.println("9. Insertar puntuación de una película");
                System.out.println("10. Insertar película");
                System.out.println("11. Modificar película");
                System.out.println("12. Eliminar película");
                System.out.println("13. Enviar XML por correo");
                System.out.println("14. Volver");
                System.out.print("Selecciona el numero: ");
                num = scanner.nextInt();
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Dato erróneo");
            }

            switch (num) {
                case 1 -> funciones.Mostrar_Peliculas(null);
                case 2 -> funciones.Mostrar_Peliculas_orden_puntuacion(null);
                case 3 -> funciones.Mostrar_Pelicula_por_ID();
                case 4 -> funciones.Mostrar_Pelicula_por_Director();
                case 5 -> funciones.Mostrar_Pelicula_por_Musico();
                case 6 -> funciones.Mostrar_Pelicula_por_Fotografo();
                case 7 -> funciones.Mostrar_Pelicula_por_Actor_Principal();
                case 8 -> funciones.Mostrar_Pelicula_por_Actor_Secundario();
                case 9 -> funciones.Puntuacion_pelicula();
                case 10 -> funciones.Insertar_pelicula();
                case 11 -> funciones.Modificar_pelicula();
                case 12 -> funciones.Eliminar_Pelicula();
                case 13 -> funciones.Mail_Peliculas();
                case 14 -> Menu_Principal();
                default -> {
                    System.out.println("Error");
                    scanner.nextLine();
                }
            }
        }
    }

    public static void Actores() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Todo_Funciones_y_Creacion_Fichero_Pelicula funciones = new Todo_Funciones_y_Creacion_Fichero_Pelicula();
        int num = 0;
        while (num != 6) {
            try {

                System.out.println("\nMenú de los actores");
                System.out.println("1. Mostrar todos los actores");
                System.out.println("2. Mostrar por id del actor");
                System.out.println("3. Insertar actor");
                System.out.println("4. Modificar actor");
                System.out.println("5. Eliminar actor");
                System.out.println("6. Volver");
                System.out.print("Selecciona el numero: ");
                num = scanner.nextInt();
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Dato erróneo");
            }

            switch (num) {
                case 1 -> funciones.Mostrar_Actores(null);
                case 2 -> funciones.Mostrar_Actor_por_Nombre();
                case 3 -> funciones.Insertar_Actor();
                case 4 -> funciones.Modificar_Actor();
                case 5 -> funciones.Eliminar_Actores();
                case 6 -> Menu_Principal();
                default -> {
                    System.out.println("Error");
                    scanner.nextLine();
                }
            }
        }
    }

    public static void Directores() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Todo_Funciones_y_Creacion_Fichero_Pelicula funciones = new Todo_Funciones_y_Creacion_Fichero_Pelicula();
        int num = 0;
        while (num != 7) {
            try {

                System.out.println("\nMenú de los directores");
                System.out.println("1. Mostrar todos los directores");
                System.out.println("2. Mostrar por id del director");
                System.out.println("3. Insertar director");
                System.out.println("4. Modificar director");
                System.out.println("5. Eliminar director");
                System.out.println("6. Volver");
                System.out.print("Selecciona el numero: ");
                num = scanner.nextInt();
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Dato erróneo");
            }

            switch (num) {
                case 1 -> funciones.Mostrar_Directores(null);
                case 2 -> funciones.Mostrar_Director_por_Nombre();
                case 3 -> funciones.Insertar_Directores();
                case 4 -> funciones.Modificar_Directores();
                case 5 -> funciones.Eliminar_Directores();
                case 6 -> Menu_Principal();
                default -> {
                    System.out.println("Error");
                    scanner.nextLine();
                }
            }
        }
    }

    public static void Fotografos() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Todo_Funciones_y_Creacion_Fichero_Pelicula funciones = new Todo_Funciones_y_Creacion_Fichero_Pelicula();
        int num = 0;
        while (num != 6) {
            try {
                System.out.println("\nMenú de los fotógrafos");
                System.out.println("1. Mostrar todos los fotógrafos");
                System.out.println("2. Mostrar por nombre del fotógrafos");
                System.out.println("3. Insertar fotógrafo");
                System.out.println("4. Modificar fotógrafo");
                System.out.println("5. Eliminar fotógrafo");
                System.out.println("6. Volver");
                System.out.print("Selecciona el numero: ");
                num = scanner.nextInt();
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Dato erróneo");
            }

            switch (num) {
                case 1 -> funciones.Mostrar_Fotografos(null);
                case 2 -> funciones.Mostrar_Fotografo_por_Nombre();
                case 3 -> funciones.Insertar_Fotografo();
                case 4 -> funciones.Modificar_Fotografos();
                case 5 -> funciones.Eliminar_Fotografo();
                case 6 -> Menu_Principal();
                default -> {
                    System.out.println("Error");
                    scanner.nextLine();
                }
            }
        }
    }

    public static void Musicos() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        Todo_Funciones_y_Creacion_Fichero_Pelicula funciones = new Todo_Funciones_y_Creacion_Fichero_Pelicula();
        int num = 0;
        while (num != 6) {
            try {
                System.out.println("\nMenú de los compositores");
                System.out.println("1. Mostrar todos los compositores");
                System.out.println("2. Mostrar por nombre del compositores");
                System.out.println("3. Insertar compositor");
                System.out.println("4. Modificar compositor");
                System.out.println("5. Eliminar compositor");
                System.out.println("6. Volver");
                System.out.print("Selecciona el numero: ");
                num = scanner.nextInt();
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Dato erróneo");
            }

            switch (num) {
                case 1 -> funciones.Mostrar_Musicos(null);
                case 2 -> funciones.Mostrar_Musico_por_Nombre();
                case 3 -> funciones.Insertar_Musico();
                case 4 -> funciones.Modificar_Musico();
                case 5 -> funciones.Eliminar_Musicos();
                case 6 -> Menu_Principal();
                default -> {
                    System.out.println("Error");
                    scanner.nextLine();
                }
            }
        }
    }
}