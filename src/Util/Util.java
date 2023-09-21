package Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

/**
 *
 * @author 2dam
 */
public class Util {

    public static String introducirCadena() {
        boolean error = false;
        String cadena = "";
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);
        do {
            error = false;
            try {
                cadena = teclado.readLine();
            } catch (IOException e) {
                error = true;
                System.out.println("Error en la entrada de datos. Vuelva a intentarlo:  ");
            }
        } while (error);
        return cadena;
    }

    public static String introducirCadena(String mensaje) {
        String cadena = "";
        InputStreamReader entrada = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(entrada);

        System.out.println(mensaje);
        try {
            cadena = teclado.readLine();
        } catch (IOException e) {
            System.out.println("Error en la entrada de datos");
        }
        return cadena;
    }

    public static String leerString(int x) {
        String cadena = null;
        boolean ok;
        do {
            ok = true;
            cadena = introducirCadena();
            if (cadena.length() > x) {
                System.out.println("Error, longitud superior a la permitida. Introduzca de nuevo: ");
                ok = false;
            }
        } while (!ok);
        return cadena;
    }

    public static int leerInt() {
        int num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, el dato no es número. Introduce de nuevo: ");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int leerInt(String mensaje) {
        int num = 0;
        boolean error;
        System.out.println(mensaje);
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, el dato no es número. Introduce de nuevo: ");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int leerInt(int x, int y) {
        int num;
        boolean error;
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, el dato no es número. Introduce de nuevo: ");
                error = true;
                num = x;
            }
            if (num < x || num > y) {
                System.out.println("Error, dato fuera de rango. Introduce de nuevo: ");
                error = true;

            }
        } while (error);
        return num;
    }

    public static int leerInt(String mensaje, int x, int y) {
        int num;
        boolean error;
        System.out.println(mensaje);
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, el dato no es número. Introduce de nuevo: ");
                error = true;
                num = x;
            }
            if (num < x || num > y) {
                System.out.println("Error, dato fuera de rango. Introduce de nuevo: ");
                error = true;

            }
        } while (error);
        return num;
    }

    public static LocalDateTime leerFechaHora() {
        String fechaAux;
        LocalDateTime fecha = null;
        boolean error;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        do {
            error = false;
            fechaAux = Util.introducirCadena();
            try {
                fecha = LocalDateTime.parse(fechaAux, formateador);
            } catch (DateTimeParseException e) {
                error = true;
                System.out.println("Error, introduce fecha y hora con formato dd/mm/aaaa hh:mm");
            }

        } while (error);
        return fecha;
    }

    public static int leetInt(String message, int min, int max) {
        int num = 0;
        boolean error;

        System.out.print(message);
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.print("Valor no numerico. Introduce de nuevo: ");
                error = true;
                num = min;
            }
            if (num < min || num > max) {
                System.out.print("Numero fuera de rango, introduce un numero entre " + min + " y " + max + ": ");
                error = true;
            }
        } while (error);

        return num;
    }

    public static boolean esBoolean(String prompt) {
        String input;
        do {
            input = introducirCadena(prompt).toLowerCase();
        } while (!isValidBooleanInput(input));

        return isAffirmative(input);
    }

    private static boolean isValidBooleanInput(String input) {
        return input.equals("0") || input.equals("1") || input.equals("si") || input.equals("no") || input.equals("s") || input.equals("n") || input.equals("true") || input.equals("false");
    }

    private static boolean isAffirmative(String input) {
        return input.equals("1") || input.equals("si") || input.equals("s") || input.equals("true");
    }

    public static int calculoFichero(File fich) {
        int cont = 0;
        if (fich.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(fich);
                ois = new ObjectInputStream(fis);

                Object aux = ois.readObject();

                while (aux != null) {
                    cont++;
                    aux = ois.readObject();
                }

            } catch (EOFException e1) {

            } catch (IOException | ClassNotFoundException e2) {
                
            }

            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar los flujos");

            }
        }
        return cont;
    }

    public static char leerChar(char opt1, char opt2) {
        char letra = ' ';
        String cadena;
        boolean error;

        do {
            error = false;
            cadena = introducirCadena();

            if (cadena.length() != 1) {
                System.out.print("Error, introduce un unico caracter: ");
                error = true;
            } else {
                letra = cadena.charAt(0);
                letra = Character.toUpperCase(letra);
                if (letra != opt1 && letra != opt2) {
                    System.out.print(
                            "Error, la opcion introducida no es correcta, introduce " + opt1 + " o " + opt2 + ": ");
                    error = true;
                }
            }
        } while (error);

        return letra;
    }

    public static char leerChar(String message, char opt1, char opt2) {
        char letra = ' ';
        String cadena;
        boolean error;

        System.out.print(message);
        do {
            error = false;
            cadena = introducirCadena();

            if (cadena.length() != 1) {
                System.out.print("Error, introduce un unico caracter: ");
                error = true;
            } else {
                letra = cadena.charAt(0);
                letra = Character.toUpperCase(letra);
                if (letra != opt1 && letra != opt2) {
                    System.out.print(
                            "Error, la opcion introducida no es correcta, introduce " + opt1 + " o " + opt2 + ": ");
                    error = true;
                }
            }
        } while (error);

        return letra;
    }

    public static char leerChar(char opt1, char opt2, char opt3) {
        char letra = ' ';
        String cadena;
        boolean error;

        do {
            error = false;
            cadena = introducirCadena();

            if (cadena.length() != 1) {
                System.out.print("Error, introduce un unico caracter: ");
                error = true;
            } else {
                letra = cadena.charAt(0);
                letra = Character.toUpperCase(letra);
                if (letra != opt1 && letra != opt2 && letra != opt3) {
                    System.out.print("Error, la opcion introducida no es correcta, introduce " + opt1 + " o " + opt2
                            + " o " + opt3 + ": ");
                    error = true;
                }
            }
        } while (error);

        return letra;
    }

    public static char leerChar(String message, char opt1, char opt2, char opt3) {
        char letra = ' ';
        String cadena;
        boolean error;

        System.out.print(message);
        do {
            error = false;
            cadena = introducirCadena();

            if (cadena.length() != 1) {
                System.out.print("Error, introduce un unico caracter: ");
                error = true;
            } else {
                letra = cadena.charAt(0);
                letra = Character.toUpperCase(letra);
                if (letra != opt1 && letra != opt2 && letra != opt3) {
                    System.out.print("Error, la opcion introducida no es correcta, introduce " + opt1 + " o " + opt2
                            + " o " + opt3 + ": ");
                    error = true;
                }
            }
        } while (error);

        return letra;
    }

    public static char leerChar() {
        char letra = ' ';
        String cadena;
        boolean error;

        do {
            error = false;
            cadena = introducirCadena();

            if (cadena.length() != 1) {
                System.out.print("Error, introduce un unico caracter: ");
                error = true;
            }
        } while (error);
        letra = cadena.charAt(0);
        return letra;
    }

}
