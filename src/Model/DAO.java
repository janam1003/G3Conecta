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

    // Method to create a UnidadDidactica object.
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager;

    // Method to create an Enunciado object.
    public long createEnunciado(Enunciado enunciado) throws ExceptionManager;

    // Method to create a ConvocatoriaExamen object.
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager;

    // Method to consult (retrieve) a UnidadDidactica object.
    public boolean ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager;

    // Method to consult (retrieve) a ConvocatoriaExamen object.
    public boolean ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager;

    // Method to consult (retrieve) ConvocatoriaExamen objects associated with a Enunciado.
    // It takes a Enunciado object as an argument to specify the search criteria.
    public List <ConvocatoriaExamen> ConsultConvocatoriasEnun(Enunciado enunciado) throws ExceptionManager;

    // Method to consult (retrieve) Enunciado objects associated with a UnidadDidactica.
    // It takes an Enunciado object as an argument to specify the search criteria.
    public List <Enunciado> ConsultEnunciadosUD(UnidadDidactica unidadDidactica) throws ExceptionManager;

	public void updateIdUEnunciadoExamen(Enunciado enunciado, ConvocatoriaExamen convocatoriaExamen);

	public String getPathEnun(Enunciado enunciado) throws ExceptionManager;

}
