package persistence;

import domain.Chest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brent
 */
public class DatabaseWriter {

    public static Connection dbConnection;
    private boolean connected;

    DatabaseWriter() {
        
    }
    
    public void saveChest(Chest c) {
        
    }
    
    private void initConnection() {
        try {
            if(dbConnection == null || dbConnection.isClosed()) {
                dbConnection = DriverManager.getConnection(PersistenceController.JDBC_URL);
            }
        } catch (SQLException ex) {
            System.out.println("initConnection failed");
        }
    }
}
