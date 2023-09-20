package Model;

import java.util.ResourceBundle;

/**
 *
 * @author Janam
 */
public class DAOFactory {

    private static DAO data;

    /**
     * Load the data variable, if it is not previously loaded
     *
     * @return data Model
     */
    public static DAO getModel() throws UnknownModelTypeException {

        switch (ResourceBundle.getBundle("Model.config").getString("MODEL")) {

            case "FILE":

                data = new DAOImplementationFich();

                break;

            case "BD":

                data = new DAOImplementationDB();

                break;

            default:

                throw new UnknownModelTypeException("That type of model is not valid.");
        }

        return data;
    }
}
