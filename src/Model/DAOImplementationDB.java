package Model;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Janam
 */
public class DAOImplementationDB implements DAO {

    private Connection con;
    private PreparedStatement stmt;
    private ConnectionOpenClose occ = new ConnectionOpenClose();

    @Override
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createEnunciado(Enunciado enunciado) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        boolean exists = false;
        final String queryUnidad = "Select id from unidad where id=?";

        con = occ.openConnection();

        try {
            stmt = con.prepareStatement(queryUnidad);
            stmt.setInt(1, unidadDidactica.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if ( unidadDidactica.getId() == rs.getInt("id")) {
                    exists = true;
                }
            }
            
        } catch (SQLException e) {
            String error = "Error checking if the Unit exists or not";
            ExceptionManager er = new ExceptionManager(error);
            throw er;

        }
        try{
        occ.closeConnection(stmt, con);
                }catch(SQLException e){}
        
        return exists;
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
