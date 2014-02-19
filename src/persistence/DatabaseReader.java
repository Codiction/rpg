
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brent
 */
public class DatabaseReader {
    
    Connection dbConnection;

    DatabaseReader() throws SQLException {
        dbConnection = DriverManager.getConnection(PersistenceController.JDBC_URL);
    }
}
