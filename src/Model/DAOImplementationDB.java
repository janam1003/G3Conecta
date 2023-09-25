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
          con = conection.openConnection();

        try {
            final String createUnidadSQL = "INSERT INTO unidad VALUES (acronimo, titulo, evaluacion, descripcion(?, ?, ?, ?)";
            stmt = con.prepareStatement(createUnidadSQL);
            stmt.setString(1, unidadDidactica.getAcronimo());
            stmt.setString(2, unidadDidactica.getTitulo());
            stmt.setString(3, unidadDidactica.getEvaluacion());
            stmt.setString(4, unidadDidactica.getDescripcion());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
			int idGenerado = 0;
            if (generatedKeys.next())
                idGenerado = generatedKeys.getInt(1);
			unidadDidactica.setId(idGenerado);
			
            stmt.close();

        } catch (SQLException e) {

            String error = "This UnidadDidactica already exist";
            ExceptionManager exp = new ExceptionManager(error);
            throw exp;
        }
    }

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
		if (generatedKeys.next())
			idGenerado = generatedKeys.getLong(1);
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


    // @Override
    // public void createEnunciado(Enunciado enunciado) throws ExceptionManager {

    //     try {
    //         final String createUnidadSQL = "INSERT INTO enunciado VALUES (id, descripcio, disponible, ruta, nivel(?, ?, ?, ?, ?)";
    //         stmt = con.prepareStatement(createUnidadSQL);
    //         stmt.setString(2, enunciado.getDescripcion());
    //         stmt.setBoolean(3, enunciado.isDisponible());
    //         stmt.setString(4, enunciado.getRuta());
    //         Enunciado getEnunciado = null;
    //         DificultadType dificultadType;
    //         dificultadType = null;
    //         for (DificultadType a : DificultadType.values()) {
    //             if (a.ordinal() == getInt("nivel")) {
    //                 dificultadType = a;
    //             }
    //         }
    //         getEnunciado.setNivel(dificultadType);
    //         stmt.executeUpdate();
    //         stmt.close();

    //     } catch (SQLException e) {

    //         String error = "This Enunciado already exist";
    //         ExceptionManager exp = new ExceptionManager(error);
    //         throw exp;
    //     }
    // }

    @Override
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        conection.closeConnection(stmt, con);
                }catch(SQLException e){}
        
        return exists;
    }

    @Override
    public boolean ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
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
}
