package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;

/**
 *
 * @author Janam
 */
public class DAOImplementationDB implements DAO {

	private Connection con;

    private PreparedStatement stmt;

    private final ConnectionOpenClose conection = new ConnectionOpenClose();

	private ResultSet rs;

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
    public void ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    //public List <ConvocatoriaExamen> ConsultConvocatoriasUD(UnidadDidactica unidadDidactica) throws ExceptionManager {
		//VACIO esta en fichero
    //}

    @Override
    public List <Enunciado> ConsultEnunciadosUD(UnidadDidactica unidadDidactica) throws ExceptionManager {
	
		con = conection.openConnection();

        try {

			Enunciado enunciado;
			ArrayList<Enunciado> list = new ArrayList<Enunciado>();

            final String SELECTenunciados = "SELECT e.* FROM enunciado e WHERE e.id = (Select enunciados_id from unidad_enunciado where unidads_id = ?";
			// Prepare the query
			stmt = con.prepareStatement(SELECTenunciados);

			stmt.setString(1, unidadDidactica.getId().toString());
			// Execute the query
			rs = stmt.executeQuery();

			while (rs.next()) {
				// We add the champ to the list
				enunciado = new Enunciado();
				enunciado.setId(rs.getInt("Id"));
				enunciado.setDescripcion(rs.getString("Descripcion"));
				enunciado.setRuta(rs.getString("Ruta"));
				enunciado.setDisponible(rs.getBoolean("Disponible"));
				//enunciado.setNivel(rs.getString("Nivel"));
				list.add(enunciado);
			}
			conection.closeConnection(stmt, con);
			return list;
        } catch (SQLException e) {

            String error = "No enunciados with this unidad didactica";
            ExceptionManager exp = new ExceptionManager(error);
            throw exp;
        }
    }
}
