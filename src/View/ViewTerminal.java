package view;

import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import classes.ConvocatoriaExamen;
import classes.Enunciado;
import classes.UnidadDidactica;
import utils.Util;

/**
 * The ViewTerminal class implements the view interface and provides a
 * terminal-based user interface for interacting with the application. It
 * displays menus, messages, and user prompts in the terminal and handles user
 * input.
 *
 * It includes methods for displaying lists of ConvocatoriaExamen and Enunciado
 * objects, presenting a menu for user selection, and managing user interaction
 * throughout the program.
 *
 * @author 2dam
 */
public class ViewTerminal implements View {

    /**
     * Displays a list of ConvocatoriaExamen objects in the terminal.
     *
     * @param convocatorias The list of ConvocatoriaExamen objects to display.
     */
    public void mostrarConvocatorias(List<ConvocatoriaExamen> convocatorias) {
        System.out.println("\nLista de convocatorias:");
        for (ConvocatoriaExamen convocatoria : convocatorias) {
            System.out.println("Convocatoria = " + convocatoria.getConvocatoria());
            System.out.println("Descripcion = " + convocatoria.getDescripcion());
            System.out.println("Fecha = " + convocatoria.getFecha().toString());
            System.out.println("Curso = " + convocatoria.getCurso() + "\n");
        }
    }

    /**
     * Displays a list of Enunciado objects in the terminal.
     *
     * @param enunciados The list of Enunciado objects to display.
     */
    public void mostrarEnunciados(List<Enunciado> enunciados) {
        System.out.println("\nLista de enunciados:");
        for (Enunciado enunciado : enunciados) {
            System.out.println("Id = " + enunciado.getId());
            System.out.println("Disponible = " + enunciado.isDisponible());
            System.out.println("Descripcion = " + enunciado.getDescripcion());
            System.out.println("Ruta = " + enunciado.getRuta());
            System.out.println("Nivel = " + enunciado.getNivel() + "\n");

        }
    }

    /**
     * Displays a menu of options related to Enunciado objects and waits for
     * user input.
     *
     * @return An integer representing the selected menu option (1 to 9).
     */
    public int menuListadosEnunciado() {
        System.out.println("\t1: Create UnidadDidactica.\n\t"
                + "2: Create Enunciado.\n\t"
                + "3: Create ConvocatoriaExamen.\n\t"
                + "4: Consult Enunciados with specefic Unidad Didactica.\n\t"
                + "5: Consult Convocatorias with specefic Enunciado.\n\t"
                + "6: Visualize document with specific Enunciado.\n\t"
                + "7: Exit.");
        return Util.leerInt("Introduce un número del 1 al 7: ", 1, 7);
    }

    /**
     * Prompts the user to continue or finish the program and returns their
     * choice.
     *
     * @return An integer representing the user's choice (0 to continue, 1 to
     * finish).
     */
    public int seguirPrograma() {
        return Util.leerInt("Write 0 to continue with the program or 1 to finish it", 0, 1);
    }

    public void terminarPrograma() {
        System.out.println("Program finished");
    }

    /**
     * Prompts the user with a message and reads an integer as user input.
     *
     * @param message The message to display to the user.
     * @return An integer representing the user's input.
     */
    public int getUserId(String message) {
        return Util.leerInt(message);
    }

    public void mostrarUnidadDidacticaNoExiste() {
        System.out.println("This Unidad Didactica doesn't exist");
    }

    /**
     * Collects and sets the data for creating an Enunciado based on user input.
     *
     * @return An Enunciado object containing the user-provided data.
     */
    @Override
    public Enunciado setDatosEnun() {
        Enunciado enunciado = new Enunciado();
        List<UnidadDidactica> unidadDidacticas = new ArrayList<>();

        enunciado.setDescripcion(Util.introducirCadena("Write the Enunciado's description"));
        enunciado.setRuta(Util.introducirCadena("Write the Enunciado's route"));
        enunciado.setNivel(Util.leerChar("Set your nivel type in A for Alta, M for Media"
                + "B for Baja: ", 'A', 'M', 'B'));
        if (Util.leerInt("Write 1 if the Enunciado is available or 0 if not", 0, 1) == 1) {
            enunciado.setDisponible(true);
        } else {
            enunciado.setDisponible(false);
        }
        while (Util.leerInt("Write 1 if you want to add a Unidad Didactica or 0 if not", 0, 1) == 1) {
            UnidadDidactica unidadDidactica = new UnidadDidactica();
            unidadDidactica.setId(Util.leerInt("Write the Unidad Didactica's id you want"));
            unidadDidacticas.add(unidadDidactica);
        }
        enunciado.setUnidadDidacticas(unidadDidacticas);
        return enunciado;
    }

    @Override
    public String askConvocatoriaExamen() {
        return Util.introducirCadena("Write the Convocatoria Examen's convocatoria you want or 0 if you don't want to add it");
    }

    @Override
    public void mostrarConvocatoriaExamenNoExiste() {
        System.out.println("This Convocatoria Examen doesn't exist");
    }

    @Override
    public long askIdEnunciado() {
        System.out.println("Write the enunciado's id you want to visualize");
        double inputValue = Util.leerDouble();
        long id = (long) inputValue;
        return id;
    }

    @Override
    public void cantFindPath() {
        System.out.println("The path for the enunciado's id provided doesn't exist");
    }

    /**
     * Displays the ID of the created enunciado.
     *
     * @param id The ID of the created enunciado.
     */
    @Override
    public void showIdEnunciado(long id) {
        System.out.println("The created enunciado's id is: " + id);
    }

    /**
     * Opens and visualizes a document located at the specified file path using
     * the default system application.
     *
     * @param path The file path of the document to be visualized.
     */
    @Override
    public void visualizeDocument(String path) {
        File file = new File(path);
        Desktop desktop = Desktop.getDesktop();
        try {
            if (file.exists() && desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(file);
            } else {
                System.out.println("The file cant be opened");
            }
        } catch (IOException e) {
            System.out.println("The file cant be opened");
        }
    }

    /**
     * Displays the ID of the created UnidadDidactica.
     *
     * @param id The ID of the created UnidadDidactica.
     */
    @Override
    public void showIdUD(Integer id) {
        System.out.println("The Unidad Didactica " + id + " is successfully created.");
    }

    /**
     * Prompts the user to input information to create a UnidadDidactica object.
     *
     * @return A new UnidadDidactica object with user-provided information.
     */
    @Override
    public UnidadDidactica setDatosUD() {

        UnidadDidactica unidadDidactica = new UnidadDidactica();

        unidadDidactica.setTitulo(Util.introducirCadena("Write the unidadDidactica titulo: "));

        unidadDidactica.setDescripcion(Util.introducirCadena("Write the unidadDidactica description: "));

        unidadDidactica.setAcronimo(Util.introducirCadena("Write the unidadDidactica acronimo: "));

        unidadDidactica.setEvaluacion(Util.introducirCadena("Write the unidadDidactica evaluacion: "));

        return unidadDidactica;
    }

    /**
     * Prompts the user to input information to create a ConvocatoriaExamen
     * object.
     *
     * @return A new ConvocatoriaExamen object with user-provided information.
     */
    @Override
    public ConvocatoriaExamen setDatosConvocatoria() {
        ConvocatoriaExamen convocatoriaExamen = new ConvocatoriaExamen();
        convocatoriaExamen.setConvocatoria(Util.introducirCadena("Insert the Convocatoria: "));
        convocatoriaExamen.setDescripcion(Util.introducirCadena("Insert the ConvocatoriaExamen descripcion: "));
        convocatoriaExamen.setFecha(Util.leerFechaAMD("Introudce la fecha con formato AAAA/MM/DD: \n"));
        convocatoriaExamen.setCurso(Util.introducirCadena("Insert the curso: "));
        return convocatoriaExamen;
    }

}
