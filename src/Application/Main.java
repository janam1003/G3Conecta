package application;

import controller.Controller;
import exceptions.ExceptionManager;
import model.DAO;
import model.DAOFactory;
import view.View;
import view.ViewFactory;

/**
 * The main entry point of the application.
 *
 * This class initializes the data access objects (DAOs), the view, and starts
 * the application by invoking the controller.
 *
 * @author Iñigo
 */
public class Main {

    /**
     * The main method of the application.
     *
     * @param args Command-line arguments (not used).
     * @throws ExceptionManager If an exception occurs during application
     * execution.
     */
    public static void main(String[] args) throws ExceptionManager {

        // Create DAO models for "Examen" and "UDyEnun"
        DAO modelExamen = DAOFactory.getModel("Examen");
        DAO modelUDyEnun = DAOFactory.getModel("UDyEnun");

        // Create a view instance
        View view = ViewFactory.getView();

        // Start the application by passing the models and view to the controller
        Controller.startApplication(modelUDyEnun, modelExamen, view);
    }
}
