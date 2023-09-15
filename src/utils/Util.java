package utils;

import java.time.LocalDate;
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
import java.time.DateTimeException;

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

    public static char leerChar() {
        boolean error = false;
        String letra;

        do {
            error = false;
            letra = introducirCadena();
            if (letra.length() != 1) {
                System.out.println("Error, introduce un car�cter: ");
                error = true;
            }

        } while (error);
        return letra.charAt(0);
    }

    public static char leerChar(String mensaje) {
        char letra;
        String frase;
        System.out.println(mensaje);
        do {

            frase = introducirCadena();
            if (frase.length() != 1) {
                System.out.println("Error, introduce un �nico car�cter: ");
            }
        } while (frase.length() != 1);
        letra = frase.charAt(0);

        return letra;
    }

    public static char leerChar(char x, char y) {

        char letra;
        String frase;
        do {
            do {
                frase = introducirCadena();
                if (frase.length() != 1) {
                    System.out.println("Error, introduce un �nico car�cter: ");
                }
            } while (frase.length() != 1);
            letra = frase.toUpperCase().charAt(0);
            if (!(letra == x || letra == y)) {
                System.out.println("Error car�cter no Valido");
            }
        } while (!(letra == x || letra == y));
        return letra;
    }

    public static char leerCharArray(char caracteres[]) {
        int i;
        boolean error = false;
        String letra;
        char aux = 0;

        do {
            error = false;
            letra = introducirCadena();
            if (letra.length() != 1) {
                System.out.println("Error, introduce un car�cter: ");
                error = true;
            } else {
                aux = letra.charAt(0);
                for (i = 0; i < caracteres.length; i++) {
                    if (Character.toUpperCase(caracteres[i]) == Character.toUpperCase(aux)) {
                        break;
                    }
                }
                if (i == caracteres.length) {
                    error = true;
                    System.out.println("Error, el car�cter introducido no es valido. ");
                }
            }
        } while (error);
        return aux;
    }

    public static int leerInt() {
        int num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Integer.parseInt(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, el dato no es num�rico. Introduce de nuevo: ");
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
                System.out.println("Error, el dato no es num�rico. Introduce de nuevo: ");
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
                System.out.println("Error, el dato no es num�rico. Introduce de nuevo: ");
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
                System.out.println("Error, el dato no es num�rico. Introduce de nuevo: ");
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

    public static float leerFloat() {
        float num = 0;
        boolean error;
        do {
            error = false;
            try {
                num = Float.parseFloat(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, el dato no es num�rico. Introduce de nuevo: ");
                error = true;
            }
        } while (error);
        return num;
    }

    public static float leerFloat(String mensaje) {
        float num = 0;
        boolean ok = true;
        System.out.println(mensaje);
        do {
            ok = true;

            try {
                num = Float.parseFloat(introducirCadena());
            } catch (NumberFormatException e) {
                ok = false;
                System.out.println("Error al introducir un n�mero");
            }
        } while (!ok);
        return num;
    }

    public static float leerFloat(float x, float y) {
        float num;
        boolean error;
        do {
            error = false;
            try {
                num = Float.parseFloat(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error, el dato no es num�rico. Introduce de nuevo: ");
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

    public static double leerDouble(double x, double y) {
        double num = 0;
        boolean ok;
        do {
            try {
                ok = true;
                num = Double.parseDouble(introducirCadena());

            } catch (NumberFormatException e) {
                System.out.println("Hay que introducir n�meros");
                ok = false;
                num = x;

            }
            if (num < x || num > y) {
                System.out.println("Dato fuera de rango, introduce entre" + x + " y " + y);
                ok = false;
            }
        } while (!ok);
        return num;
    }

    public static double leerDouble() {
        double fNumero = 0;
        boolean ok;
        do {
            try {
                ok = true;
                fNumero = Double.parseDouble(introducirCadena());
            } catch (NumberFormatException e) {
                System.out.println("Error al introducir el n?mero");
                ok = false;
            }
        } while (!ok);
        return fNumero;
    }

    public static boolean esBoolean() {
        String respu;
        do {
            respu = introducirCadena().toLowerCase();
        } while (!respu.equals("0") && !respu.equals("1") && !respu.equals("si") && !respu.equals("no") && !respu.equals("s") && !respu.equals("n") && !respu.equals("true") && !respu.equals("false"));
        if (respu.equals("1") || respu.equals("si") || respu.equals("s") || respu.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public static LocalDate leerFecha() {
        String fechaAux;
        LocalDate fecha = LocalDate.now();
        boolean error;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            error = false;
            fechaAux = Util.introducirCadena();
            try {
                fecha = LocalDate.parse(fechaAux, formateador);
            } catch (DateTimeParseException e) {
                error = true;
                System.out.println("Error,Introduce fecha con formato dd/mm/aaaa: ");
            }
        } while (error);
        return fecha;
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

    public static LocalDate leerFecha(String mensaje) {
        String fechaAux;
        LocalDate fechaNac = LocalDate.now();
        boolean error;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(mensaje);
        do {
            error = false;
            fechaAux = Util.introducirCadena();
            try {
                fechaNac = LocalDate.parse(fechaAux, formateador);
            } catch (DateTimeParseException e) {
                error = true;
                System.out.println("Error,Introduce fecha con formato dd/mm/aaaa: ");
            }
        } while (error);
        return fechaNac;
    }

    public static String fechaToString(LocalDate fecha) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String wfecha;

        wfecha = fecha.format(formateador);

        return wfecha;
    }

    public static String comprobarDni() {

        String dniLetra;
        String dniSinLetra;
        boolean correcto = false;
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int resto, dni = 0;
        char respuesta;

        System.out.println("Introduce el dni:");
        do {
            do {
                dniLetra = Util.introducirCadena();

                dniSinLetra = dniLetra.substring(0, dniLetra.length() - 1);
                if (dniSinLetra.charAt(0) == 'X') {
                    dniSinLetra = dniSinLetra.replace('X', '0');
                } else if (dniSinLetra.charAt(0) == 'Y') {
                    dniSinLetra = dniSinLetra.replace('Y', '1');
                } else if (dniSinLetra.charAt(0) == 'Z') {
                    dniSinLetra = dniSinLetra.replace('Z', '2');
                }
                try {
                    dni = Integer.parseInt(dniSinLetra);
                    correcto = true;
                } catch (ClassCastException e) {
                    System.out.println("DNI incorrecto, prueba otra vez.");
                    correcto = false;
                }
            } while (!correcto);

            resto = dni % 23;
            if (dniLetra.charAt(dniLetra.length() - 1) == letras.charAt(resto)) {
                System.out.println("NIF correcto");
                correcto = true;
            } else {
                System.out.println("Error, la letra que le corresponde es: " + letras.charAt(resto));
                System.out.println("�Es correcto?");
                respuesta = Util.leerChar();
                if (respuesta == 'S') {
                    correcto = true;
                } else {
                    System.out.println("Vuelve a introducir el DNI:");
                    correcto = false;
                }

            }
        } while (!correcto);

        return dniLetra;
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

            } catch (Exception e2) {
                e2.printStackTrace();
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

    public static Double leerDouble(String insert_the_amount_to_operate_with_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static LocalDate leerFechaAMD(String message) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = null;
        String dateString;
        boolean error;

        System.out.print(message);
        do {
            error = false;
            dateString = introducirCadena();
            try {
                date = LocalDate.parse(dateString, formateador);
            } catch (DateTimeException e) {
                System.out.print("Error, introduce fecha con formato aaaa/mm/dd: ");
                error = true;
            }
        } while (error);

        return date;
    }

    public static LocalDate leerFechaAMD() {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = null;
        String dateString;
        boolean error;

        do {
            error = false;
            dateString = introducirCadena();
            try {
                date = LocalDate.parse(dateString, formateador);
            } catch (DateTimeException e) {
                System.out.print("Error, introduce fecha con formato aaaa/mm/dd: ");
                error = true;
            }
        } while (error);

        return date;
    }

    public static LocalDate leerFechaDMA(String message) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        String dateString;
        boolean error;

        System.out.print(message);
        do {
            error = false;
            dateString = introducirCadena();
            try {
                date = LocalDate.parse(dateString, formateador);
            } catch (DateTimeException e) {
                System.out.print("Error, introduce fecha con formato dd/mm/aaaa: ");
                error = true;
            }
        } while (error);

        return date;
    }

    public static LocalDate leerFechaDMA() {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        String dateString;
        boolean error;

        do {
            error = false;
            dateString = introducirCadena();
            try {
                date = LocalDate.parse(dateString, formateador);
            } catch (DateTimeException e) {
                System.out.print("Error, introduce fecha con formato dd/mm/aaaa: ");
                error = true;
            }
        } while (error);

        return date;
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

    public static Double leerDouble(Double min, Double max) {
        Double num = 0.00;
        boolean error;

        do {
            error = false;
            try {
                num = Double.parseDouble(introducirCadena());
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

    public static Double leerDouble(String message, Double min, Double max) {
        Double num = 0.00;
        boolean error;
        System.out.print(message);
        do {
            error = false;
            try {
                num = Double.parseDouble(introducirCadena());
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

}
