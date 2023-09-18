package Application;

import Controller.Controller;
import Model.DAOFactory;

/**
 *
 * @author Janam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            new Controller().run(DAOFactory.getModel());

        } catch (Exception ex) {

            System.out.println(ex.getLocalizedMessage());

        }
    }
}

