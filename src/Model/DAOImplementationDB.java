package Model;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

            final String createUnidadSQL = "INSERT INTO unidad VALUES (acronimo, titulo, evaluacion, descripcion(?, ?, ?, ?)";
            stmt = con.prepareStatement(createUnidadSQL);
            stmt.setString(2, unidadDidactica.getAcronimo());
            stmt.setString(3, unidadDidactica.getTitulo());
            stmt.setString(4, unidadDidactica.getEvaluacion());
            stmt.setString(5, unidadDidactica.getDescripcion());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int idGenerado = 0;
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
            }
            unidadDidactica.setId(idGenerado);

            conection.closeConnection(stmt, con);

        } catch (SQLException e1) {

            String error = "This Unidad Didactica already exists.";
            ExceptionManager exp = new ExceptionManager(error);
            throw exp;
        }

    }

    @Override
    public void createEnunciado(Enunciado enunciado) throws ExceptionManager {

        con = conection.openConnection();

        try {

            final String createEnunciadoSQL = "INSERT INTO enunciado VALUES (disponible, descripcion, ruta, nivel(?, ?, ?, ?)";
            stmt = con.prepareStatement(createEnunciadoSQL);
            
            stmt.setString(2, enunciado.getDescripcion());
            stmt.setString(4, enunciado.getNivel().toString());
            stmt.setBoolean(3, enunciado.isDisponible());
            stmt.setString(5, enunciado.getRuta());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int idGenerado = 0;
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
            }
            enunciado.setId(idGenerado);

            conection.closeConnection(stmt, con);

        } catch (SQLException e) {

            String error = "This Enunciado already exists.";
            ExceptionManager exp = new ExceptionManager(error);
            throw exp;
        }

    }

    @Override
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {

        // Only in File
    }

    @Override
    public boolean ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {

        boolean exists = false;

        final String queryUnidad = "Select id from unidad where id=?";

        con = conection.openConnection();

        try {

            stmt = con.prepareStatement(queryUnidad);
            stmt.setInt(1, unidadDidactica.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (unidadDidactica.getId() == rs.getInt("id")) {
                    exists = true;
                }
            }

            conection.closeConnection(stmt, con);

        } catch (SQLException e) {
            String error = "Error checking if the Unit exists or not";
            ExceptionManager er = new ExceptionManager(error);
            throw er;

        }

        return exists;
    }

    @Override
    public ConvocatoriaExamen ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ConvocatoriaExamen> ConsultConvocatoriasUD(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Enunciado> ConsultEnunciadosUD(UnidadDidactica unidadDidactica) throws ExceptionManager {

        con = conection.openConnection();

        ResultSet rs;

        try {

            Enunciado enunciado;

            ArrayList<Enunciado> list = new ArrayList<>();

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
                // enunciado.setNivel(rs.getString("Nivel"));
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
