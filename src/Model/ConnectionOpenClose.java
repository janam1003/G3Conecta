package Model;

import Exceptions.ExceptionManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author 2dam
 */
public class ConnectionOpenClose {

    protected ResourceBundle configFile;
    protected String url;
    protected String user;
    protected String pass;
    protected PreparedStatement stmt;

    public ConnectionOpenClose() {

        configFile = ResourceBundle.getBundle("Model.config");

        url = configFile.getString("URL");

        user = configFile.getString("USER");

        pass = configFile.getString("PASSWORD");

    }

    public Connection openConnection() throws ExceptionManager {

        Connection connect = null;

        try {

            connect = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {

            throw new ExceptionManager(e.getMessage());

        }

        return connect;
    }

    public void closeConnection(PreparedStatement stmt, Connection con) throws SQLException {

        if (stmt != null) {

            stmt.close();
        }

        if (con != null) {

            con.close();
        }
    }
}
