package Model;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;

/**
 *
 * @author Janam
 */
public interface DAO {

    // Method to create a UnidadDidactica object.
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager;

    // Method to create an Enunciado object.
    public void createEnunciado(Enunciado enunciado) throws ExceptionManager;

    // Method to create a ConvocatoriaExamen object.
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager;

    // Method to consult (retrieve) a UnidadDidactica object.
    public void ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager;

    // Method to consult (retrieve) a ConvocatoriaExamen object.
    public void ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager;

    // Method to consult (retrieve) ConvocatoriaExamen objects associated with a UnidadDidactica.
    // It takes a ConvocatoriaExamen object as an argument to specify the search criteria.
    public void ConsultConvocatoriasUD(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager;

    // Method to consult (retrieve) Enunciado objects associated with a UnidadDidactica.
    // It takes an Enunciado object as an argument to specify the search criteria.
    public void ConsultEnunciadosUD(Enunciado enunciado) throws ExceptionManager;

}
