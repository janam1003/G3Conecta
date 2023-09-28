package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import classes.ConvocatoriaExamen;
import classes.Enunciado;
import classes.UnidadDidactica;
import exceptions.ExceptionManager;

/**
 * The DAOImplementationDB class is an implementation of the DAO (Data Access
 * Object) interface specifically for database operations. It manages database
 * connections and provides methods for interacting with the database to perform
 * CRUD (Create, Read, Update, Delete) operations on various entities.
 *
 * <p>
 * This class uses a database connection pool managed by the ConnectionOpenClose
 * class to efficiently handle database connections and operations.
 * </p>
 *
 * @see DAO
 * @see ConnectionOpenClose
 */
public class DAOImplementationDB implements DAO {

    private Connection con;

    private PreparedStatement stmt;

    private final ConnectionOpenClose conection = new ConnectionOpenClose();

    private ResultSet rs;

    /**
     * Inserts a new UnidadDidactica record into the database.
     *
     * @param unidadDidactica The UnidadDidactica object to be inserted into the
     * database.
     * @throws ExceptionManager If an exception occurs during the insertion
     * process.
     */
    @Override
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        ResultSet generatedKeys = null;

        try {

            // Establish a database connection
            con = conection.openConnection();

            // Define the SQL statement for inserting the unidad didactica
            final String createUnidadSQL = "INSERT INTO unidad (acronimo, titulo, evaluacion, descripcion) VALUES (?, ?, ?, ?)";

            // Create a PreparedStatement to execute the SQL query
            stmt = con.prepareStatement(createUnidadSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set the parameters for the PreparedStatement
            stmt.setString(1, unidadDidactica.getAcronimo());
            stmt.setString(2, unidadDidactica.getTitulo());
            stmt.setString(3, unidadDidactica.getEvaluacion());
            stmt.setString(4, unidadDidactica.getDescripcion());

            // Execute the INSERT query
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {

                throw new SQLException("Creating unidad didactica failed, no rows affected.");
            }

            // Retrieve the generated keys 
            generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {

                // Get the generated ID
                int idGenerado = generatedKeys.getInt(1);

                // Set the ID in the UnidadDidactica object
                unidadDidactica.setId(idGenerado);

            } else {

                // the case where no keys were generated 
                throw new SQLException("Creating unidad didactica failed, no ID obtained.");
            }

            // Close the connection & PreparedStatement
            conection.closeConnection(stmt, con);

        } catch (SQLException e) {

            // database-related exceptions 
            String error = "This Unidad Didactica already exists.";
            ExceptionManager exp = new ExceptionManager(error);
            throw exp;

        }
    }

    /**
     * Creates a new Enunciado record in the database and associates it with any
     * specified UnidadDidactica objects.
     *
     * @param enunciado The Enunciado object to be created and stored in the
     * database.
     * @return The ID of the newly created Enunciado.
     * @throws ExceptionManager If an exception occurs during the creation
     * process.
     */
    @Override
    public long createEnunciado(Enunciado enunciado) throws ExceptionManager {
        con = conection.openConnection();

        try {
            final String createEnunciadoSQL = "INSERT INTO enunciado (descripcion, disponible, ruta, nivel) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(createEnunciadoSQL, stmt.RETURN_GENERATED_KEYS);
            stmt.setString(1, enunciado.getDescripcion());
            stmt.setBoolean(2, enunciado.isDisponible());
            stmt.setString(3, enunciado.getRuta());
            stmt.setInt(4, enunciado.getNivel().ordinal());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            long idGenerado = 0;
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getLong(1);
            }
            enunciado.setId(idGenerado);

            conection.closeConnection(stmt, con);

            if (enunciado.getUnidadDidacticas() != null) {
                con = conection.openConnection();
                for (UnidadDidactica unidadDidactica : enunciado.getUnidadDidacticas()) {
                    final String createUnidadEnunciadoSQL = "INSERT INTO unidad_enunciado (unidads_id, enunciados_id) VALUES (?, ?)";
                    stmt = con.prepareStatement(createUnidadEnunciadoSQL);
                    stmt.setInt(1, unidadDidactica.getId());
                    stmt.setLong(2, enunciado.getId());
                    stmt.executeUpdate();
                }
                conection.closeConnection(stmt, con);
            }
            return idGenerado;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionManager("Error creating enunciado");
        }
    }

    @Override
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Checks if a specific UnidadDidactica object exists in the database by its
     * ID.
     *
     * @param unidadDidactica The UnidadDidactica object to check for existence.
     * @return True if the UnidadDidactica exists; false otherwise.
     * @throws ExceptionManager If an exception occurs during the database
     * query.
     */
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

        } catch (SQLException e) {
            String error = "Error checking if the Unit exists or not";
            ExceptionManager er = new ExceptionManager(error);
            throw er;

        }
        try {
            conection.closeConnection(stmt, con);
        } catch (SQLException e) {
        }

        return exists;
    }

    @Override
    public boolean ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Retrieves a list of Enunciado objects associated with a specific
     * UnidadDidactica from the database.
     *
     * @param unidadDidactica The UnidadDidactica object used as a filter for
     * the Enunciados.
     * @return A list of Enunciado objects associated with the given
     * UnidadDidactica.
     * @throws ExceptionManager If an exception occurs during the database
     * query.
     */
    @Override
    public List<Enunciado> ConsultEnunciadosUD(UnidadDidactica unidadDidactica) throws ExceptionManager {

        con = conection.openConnection();

        try {

            Enunciado enunciado;
            ArrayList<Enunciado> list = new ArrayList<Enunciado>();

            final String SELECTenunciados = "SELECT e.* FROM enunciado e WHERE e.id = (Select enunciados_id from unidad_enunciado where unidads_id = ?)";
            // Prepare the query
            stmt = con.prepareStatement(SELECTenunciados);

            stmt.setInt(1, unidadDidactica.getId());
            // Execute the query
            rs = stmt.executeQuery();

            while (rs.next()) {
                // We add the champ to the list
                enunciado = new Enunciado();
                enunciado.setId(rs.getLong("Id"));
                enunciado.setDescripcion(rs.getString("Descripcion"));
                enunciado.setRuta(rs.getString("Ruta"));
                enunciado.setDisponible(rs.getBoolean("Disponible"));
                if (rs.getInt("nivel") == 0) {
                    enunciado.setNivel('A');
                } else if (rs.getInt("nivel") == 1) {
                    enunciado.setNivel('M');
                } else if (rs.getInt("nivel") == 2) {
                    enunciado.setNivel('B');
                }
                list.add(enunciado);
            }
            conection.closeConnection(stmt, con);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            String error = "No enunciados with this unidad didactica";
            ExceptionManager exp = new ExceptionManager(error);
            throw exp;
        }
    }

    @Override
    public List<ConvocatoriaExamen> ConsultConvocatoriasEnun(Enunciado enunciado) throws ExceptionManager {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ConsultConvocatoriasEnun'");
    }

    @Override
    public void updateIdUEnunciadoExamen(Enunciado enunciado, ConvocatoriaExamen convocatoriaExamen) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateIdUEnunciadoExamen'");
    }

    /**
     * Retrieves the file path associated with a specific Enunciado from the
     * database.
     *
     * @param enunciado The Enunciado object for which to retrieve the file
     * path.
     * @return The file path of the Enunciado's associated document, or null if
     * not found.
     * @throws ExceptionManager If an exception occurs during the database
     * query.
     */
    @Override
    public String getPathEnun(Enunciado enunciado) throws ExceptionManager {
        con = conection.openConnection();
        String path = null;

        try {
            final String createEnunciadoSQL = "SELECT e.ruta FROM enunciado e where e.id = ?";
            stmt = con.prepareStatement(createEnunciadoSQL);
            stmt.setLong(1, enunciado.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                path = rs.getString("Ruta");
            }

            conection.closeConnection(stmt, con);

            return path;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionManager("Error searching for enunciado path on DB");
        }
    }
}
