package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import exceptions.ExceptionManager;

/**
 * The ConnectionOpenClose class is responsible for managing database
 * connections. It provides methods for opening and closing database
 * connections, as well as handling configuration properties.
 *
 * <p>
 * This class reads database configuration properties from a resource bundle and
 * uses them to establish connections to the database. It also provides a method
 * for closing connections and prepared statements safely.
 * </p>
 *
 * 2dam
 *
 * @since 1.0
 * @version 1.0
 */
public class ConnectionOpenClose {

    // Configuration properties and variables
    protected ResourceBundle configFile;
    protected String url;
    protected String user;
    protected String pass;
    protected PreparedStatement stmt;

    public ConnectionOpenClose() {

        /**
         * Constructor to initialize configuration properties from a resource
         * bundle. The resource bundle should contain database connection
         * details.
         */
        configFile = ResourceBundle.getBundle("Model.config");

        url = configFile.getString("URL");

        user = configFile.getString("USER");

        pass = configFile.getString("PASSWORD");

    }

    /**
     * Opens a database connection using the configured URL, username, and
     * password.
     *
     * @return A database connection.
     * @throws ExceptionManager If an exception occurs while opening the
     * connection.
     */
    public Connection openConnection() throws ExceptionManager {

        Connection connect = null;

        try {

            connect = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {

            throw new ExceptionManager(e.getMessage());

        }

        return connect;
    }

    /**
     * Closes a database connection and prepared statement.
     *
     * @param stmt The PreparedStatement to close (can be null).
     * @param con The Connection to close (can be null).
     * @throws SQLException If an exception occurs while closing the connection
     * or statement.
     */
    public void closeConnection(PreparedStatement stmt, Connection con) throws SQLException {

        if (stmt != null) {

            stmt.close();
        }

        if (con != null) {

            con.close();
        }
    }
}
