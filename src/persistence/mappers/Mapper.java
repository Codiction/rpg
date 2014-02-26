package persistence.mappers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Brent
 */
public class Mapper {

    private String dbLink;
    private Connection conn;

    /**
     * <p>Constructs the mapper</p>
     * <p>The <code>Mapper</code> is the super type of all mappers ({@link ChestMapper} and {@link MonsterMapper})</p>
     * @param dbUrl the connection string that will be used to connect to the MySQL database
     */
    public Mapper(String dbUrl) {
        dbLink = dbUrl;
    }

    /**
     * Opens the {@link Connection} to the MySQL Database with the specified connection string: {@code dbLink}
     */
    public void openConnection() {
        try {
            if (conn == null || !conn.isValid(100)) {
                conn = DriverManager.getConnection(dbLink);
            }
        } catch (SQLException ex) {
            System.out.println("Failed to open connection. (Mapper)");
            //TODO: handle with logger
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Failed to close connection. (Mapper)");
            //TODO: handle with logger
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        try {
            return conn.prepareStatement(sql);
        } catch (Exception e) {
            return null;
            
            //TODO: maak proper
        }
    }

}
