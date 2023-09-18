package Model;

import Classes.ConvocatoriaExamen;
import Classes.DificultadType;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static javax.swing.UIManager.getInt;

/**
 *
 * @author Janam
 */
public class DAOImplementationDB implements DAO {

    private Connection con;

    private PreparedStatement stmt;

    private final ConnectionOpenClose conection = new ConnectionOpenClose();

    @Override
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {

        con = conection.openConnection();

        try {
            final String createUnidadSQL = "INSERT INTO unidad VALUES (id, acronimo, titulo, evaluacion, descripcion(?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(createUnidadSQL);
            stmt.setString(2, unidadDidactica.getAcronimo());
            stmt.setString(3, unidadDidactica.getTitulo());
            stmt.setString(4, unidadDidactica.getEvaluacion());
            stmt.setString(5, unidadDidactica.getDescripcion());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {

            String error = "This UnidadDidactica already exist";
            ExceptionManager exp = new ExceptionManager(error);
            throw exp;
        }

    }

    @Override
    public void createEnunciado(Enunciado enunciado) throws ExceptionManager {

        try {
            final String createUnidadSQL = "INSERT INTO enunciado VALUES (id, descripcio, disponible, ruta, nivel(?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(createUnidadSQL);
            stmt.setString(2, enunciado.getDescripcion());
            stmt.setBoolean(3, enunciado.isDisponible());
            stmt.setString(4, enunciado.getRuta());
            Enunciado getEnunciado = null;
            DificultadType dificultadType;
            dificultadType = null;
            for (DificultadType a : DificultadType.values()) {
                if (a.ordinal() == getInt("type")) {
                    dificultadType = a;
                }
            }
            getEnunciado.setNivel(dificultadType);
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {

            String error = "This Enunciado already exist";
            ExceptionManager exp = new ExceptionManager(error);
            throw exp;
        }
    }

    @Override
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        // It is only done in File 
    }

    @Override
    public void ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConsultConvocatoriasUD(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConsultEnunciadosUD(Enunciado enunciado) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
