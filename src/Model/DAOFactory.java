package Model;

/**
 *
 * @author 2dam
 */
public class DAOFactory {

    private static DAO data;

    /**
     *
     * @return data Model
     * @throws UnknownModelTypeException
     */
    public static DAO getModel(String option) throws UnknownModelTypeException {

        switch (option) {

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
