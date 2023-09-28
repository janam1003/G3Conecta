package model;

import java.sql.SQLException;
import java.util.List;

import classes.ConvocatoriaExamen;
import classes.Enunciado;
import classes.UnidadDidactica;
import exceptions.ExceptionManager;

/**
 *
 * @author Janam
 */
public interface DAO {

    /**
     * Creates a new UnidadDidactica object in the data store.
     *
     * @param unidadDidactica The UnidadDidactica object to be created.
     * @throws ExceptionManager If an exception occurs during the creation
     * process.
     */
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager;

    /**
     * Creates a new Enunciado object in the data store.
     *
     * @param enunciado The Enunciado object to be created.
     * @return The ID of the newly created Enunciado.
     * @throws ExceptionManager If an exception occurs during the creation
     * process.
     */
    public long createEnunciado(Enunciado enunciado) throws ExceptionManager;

    /**
     * Creates a new ConvocatoriaExamen object in the file.
     *
     * @param convocatoriaExamen The ConvocatoriaExamen object to be created.
     * @throws ExceptionManager If an exception occurs during the creation
     * process.
     */
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager;

    /**
     * Checks if a specific UnidadDidactica object exists in the data store.
     * Returns true if found, or false if not found or if there are any errors
     * during the process.
     *
     * @param unidadDidactica The UnidadDidactica object to check for existence.
     * @return True if the UnidadDidactica object is found; false otherwise.
     * @throws ExceptionManager If an exception occurs during the process.
     */
    public boolean ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager;

    /**
     * Checks if a specific ConvocatoriaExamen object exists in the file.
     * Returns true if found, or false if not found or if there are any errors
     * during the process.
     *
     * @param convocatoriaExamen The ConvocatoriaExamen object to check for
     * existence.
     * @return True if the ConvocatoriaExamen object is found; false otherwise.
     * @throws ExceptionManager If an exception occurs during the process.
     */
    public boolean ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager;

    /**
     * Retrieves a list of ConvocatoriaExamen objects associated with a specific
     * Enunciado.
     *
     * @param enunciado The Enunciado object used as a search criteria.
     * @return A list of ConvocatoriaExamen objects associated with the given
     * Enunciado.
     * @throws ExceptionManager If an exception occurs during the retrieval
     * process.
     */
    public List<ConvocatoriaExamen> ConsultConvocatoriasEnun(Enunciado enunciado) throws ExceptionManager;

    /**
     * Retrieves a list of Enunciado objects associated with a specific
     * UnidadDidactica.
     *
     * @param unidadDidactica The UnidadDidactica object used as a search
     * criteria.
     * @return A list of Enunciado objects associated with the given
     * UnidadDidactica.
     * @throws ExceptionManager If an exception occurs during the retrieval
     * process.
     */
    public List<Enunciado> ConsultEnunciadosUD(UnidadDidactica unidadDidactica) throws ExceptionManager;

    /**
     * Updates the association between an Enunciado and a ConvocatoriaExamen by
     * setting the Enunciado's ID in the ConvocatoriaExamen object.
     *
     * @param enunciado The Enunciado to associate with the ConvocatoriaExamen.
     * @param convocatoriaExamen The ConvocatoriaExamen to update with the
     * Enunciado's ID.
     */
    public void updateIdUEnunciadoExamen(Enunciado enunciado, ConvocatoriaExamen convocatoriaExamen);

    /**
     * Retrieves the path to the text document associated with a specific
     * Enunciado.
     *
     * @param enunciado The Enunciado object used to identify the document's
     * path.
     * @return The path to the text document, or null if the path is not found.
     * @throws ExceptionManager If an exception occurs during the retrieval
     * process.
     */
    public String getPathEnun(Enunciado enunciado) throws ExceptionManager;

}
