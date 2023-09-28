package model;

import java.util.ResourceBundle;

/**
 * Retrieves a specific implementation of the DAO (Data Access Object) based on
 * the provided model type.
 *
 * 2dam
 */
public class DAOFactory {

    public static DAO getModel(String modelType) {
        String type = null;
        DAO model = null;
        ResourceBundle config = ResourceBundle.getBundle("model.config");

        if (modelType.equals("Examen")) {
            type = config.getString("MODELEXAMEN");
        } else if (modelType.equals("UDyEnun")) {
            type = config.getString("MODELUDENUN");
        }

        if (type.equals("DB")) {
            model = new DAOImplementationDB();
        } else if (type.equals("FILE")) {
            model = new DAOImplementationFich();
        }
        return model;
    }
}
